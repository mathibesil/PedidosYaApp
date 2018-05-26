package com.prueba.besil.pedidosyaprueba.ui.home.view


import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.Place
import com.google.android.gms.location.places.ui.PlacePicker
import com.prueba.besil.pedidosyaprueba.R
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Datos
import com.prueba.besil.pedidosyaprueba.ui.base.view.BaseFragment
import com.prueba.besil.pedidosyaprueba.ui.home.interactor.HomeMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.home.presenter.HomePresenter
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainMVPView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject


class HomeFragment : BaseFragment(), HomeMVPView {
    //region vars
    @Inject
    lateinit var presenter: HomePresenter<HomeMVPView, HomeMVPInteractor>
    @Inject
    lateinit var adapterRestaurante: MainAdapterRestaurante

    lateinit var mainInterface: MainMVPView

    private var REQUEST_LOCATION_CODE = 101
    lateinit var mLocation: Location
    val PLACE_PICKER_REQUEST = 1
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    //endregion
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun setUp() {
        presenter.onAttach(this)
        if (isOnline()) {
            setUpStart()
        } else {
            AlertDialog.Builder(this.context!!)
                    .setTitle("Se necesita internet")
                    .setMessage("La aplicación necesita internet para poder funcionar")
                    .setCancelable(false)
                    .setNegativeButton("Salir", DialogInterface.OnClickListener { _, _ ->
                        getBaseActivity()?.finish()
                    })
                    .create()
                    .show()
        }
    }

    private fun setUpStart() {
        presenter.getToken("trivia_f", "PeY@@Tr1v1@943")

        //region Location
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.context!!)
        fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    mLocation = location!!
                    firstLoadRestaurantes()
                }
        //endregion

        //region RecyclerView
        val mGridLayoutManager = GridLayoutManager(this.context, 1)
        rvPrincipal.setLayoutManager(mGridLayoutManager) //poner en presenter ELIMINAR
        rvPrincipal.adapter = adapterRestaurante
        rvPrincipal.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (mGridLayoutManager.findLastVisibleItemPosition() == adapterRestaurante.itemCount - 1 && adapterRestaurante.total > adapterRestaurante.offset && !adapterRestaurante.isLoading) {
                    adapterRestaurante.isLoading = true
                    getDatos()
                }
            }
        })
        home_fragment_swiperefresh.setOnRefreshListener {
            if (!adapterRestaurante.isLoading) {
                adapterRestaurante.isLoading = true
                firstLoadRestaurantes()
            } else swipeRefreshOff()
        }
        //endregion
        getBaseActivity()!!.toolbar_text.text = getString(R.string.toolbarTitle)
        getBaseActivity()!!.toolbar_text.setOnClickListener {
            val builder = PlacePicker.IntentBuilder()
            loadProgress(true)
            startActivityForResult(builder.build(this.getBaseActivity()), PLACE_PICKER_REQUEST)
        }
        getBaseActivity()?.setSupportActionBar(toolbar_home)
    }

    companion object {
        internal val TAG = "HomeFragment"
        fun newInstance(): HomeFragment = HomeFragment()
    }

    override fun onDestroyView() {
        presenter.onDetach()
        super.onDestroyView()
    }

    protected fun isOnline(): Boolean {
        val cm = getBaseActivity()?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    //region LoadData
    override fun firstLoadRestaurantes() {
        adapterRestaurante.restauranteList.clear()
        adapterRestaurante.offset = 0
        getDatos()
    }

    override fun getDatos() {
        presenter.getDatos(mLocation.latitude.toString(), mLocation.longitude.toString(), adapterRestaurante.offset)
    }

    override fun updateRestaurantes(datos: Datos) {
        adapterRestaurante.restauranteList.addAll(datos.data)
        adapterRestaurante.total = datos.total
        adapterRestaurante.offset = adapterRestaurante.offset + 20
        adapterRestaurante.isLoading = false
        adapterRestaurante.notifyDataSetChanged()
        mainInterface.setData(adapterRestaurante.restauranteList, mLocation)
        val total: String = "${datos.total.toString()} RESTAURANTES"
        getBaseActivity()!!.toolbar_total.text = total
    }
    //endregion

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                val place: Place = PlacePicker.getPlace(this.context, data)
                mLocation.latitude = place.latLng.latitude
                mLocation.longitude = place.latLng.longitude
                firstLoadRestaurantes()
            }
        }
    }

    override fun getMainInterfaceData(mainInterface: MainMVPView) {
        this.mainInterface = mainInterface
    }

    override fun showMessage(texto: String) {
        Toast.makeText(context, texto, Toast.LENGTH_SHORT).show()
    }

    override fun loadMoreProgress(enabled: Boolean) {
        if (enabled)
            pbLoadMore.visibility = View.VISIBLE
        else
            pbLoadMore.visibility = View.GONE
    }

    override fun loadProgress(enabled: Boolean) {
        if (enabled)
            pbRestaurantes.visibility = View.VISIBLE
        else
            pbRestaurantes.visibility = View.GONE
    }

    override fun swipeRefreshOff() {
        home_fragment_swiperefresh.isRefreshing = false
    }

    override fun scrollToTop() {
        rvPrincipal.smoothScrollToPosition(0)
    }




    override fun blockUi() {
    }

    override fun unBlockUi() {
    }

    override fun openLoginActivity() {
    }
}