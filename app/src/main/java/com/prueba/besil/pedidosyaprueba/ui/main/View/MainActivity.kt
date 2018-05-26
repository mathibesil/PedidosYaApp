package com.prueba.besil.pedidosyaprueba.ui.main.View

import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.Toast
import com.prueba.besil.pedidosyaprueba.R
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Restaurante
import com.prueba.besil.pedidosyaprueba.ui.base.view.BaseActivity
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeFragment
import com.prueba.besil.pedidosyaprueba.ui.home.view.MapFragment
import com.prueba.besil.pedidosyaprueba.ui.main.Interactor.MainMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.main.Presenter.MainMVPPresenter
import com.prueba.besil.pedidosyaprueba.util.addFragment
import com.prueba.besil.pedidosyaprueba.util.hideFragment
import com.prueba.besil.pedidosyaprueba.util.removeFragment
import com.prueba.besil.pedidosyaprueba.util.showFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), MainMVPView, HasSupportFragmentInjector {
    //region vars
    @Inject
    lateinit var presenter: MainMVPPresenter<MainMVPView, MainMVPInteractor>
    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    lateinit var datos: List<Restaurante>
    lateinit var actualLocation: Location
    private var actualFragment: String = ""
    private var REQUEST_LOCATION_CODE = 101
    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkLocationPermission()
        mainNavigationn.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        mainNavigationn.selectedItemId = R.id.navigation_home
        presenter.onAttach(this)
        setSupportActionBar(toolbar_home)
    }
    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {

                toolbar_home.visibility= View.VISIBLE
                appBarLayout_home.setExpanded(true, true)
                if (actualFragment.equals(HomeFragment.TAG)) {
                    val f: HomeFragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment
                    f.scrollToTop()
                } else {
                    supportFragmentManager.hideFragment(actualFragment)
                    actualFragment = HomeFragment.TAG
                    if (supportFragmentManager.findFragmentByTag(HomeFragment.TAG) == null) {
                        val newHomeFragment = HomeFragment.newInstance()
                        supportFragmentManager.addFragment(R.id.cl_root_view, newHomeFragment, HomeFragment.TAG)
                        newHomeFragment.getMainInterfaceData(this)
                    }
                    else
                        supportFragmentManager.showFragment(HomeFragment.TAG)
                }


                return@OnNavigationItemSelectedListener true
            }

            R.id.navigation_notifications2 -> {
                if (!actualFragment.equals(MapFragment.TAG)) {
                    supportFragmentManager.hideFragment(actualFragment)
                    toolbar_home.visibility= View.GONE
                    actualFragment = MapFragment.TAG
                    if (supportFragmentManager.findFragmentByTag(MapFragment.TAG) == null){
                        val newMapFragment= MapFragment.newInstance()
                        supportFragmentManager.addFragment(R.id.cl_root_view, newMapFragment, MapFragment.TAG)
                        newMapFragment.setData(datos,actualLocation)
                    }
                    else{
                        supportFragmentManager.showFragment(MapFragment.TAG)
                        val f: MapFragment = supportFragmentManager.findFragmentByTag(MapFragment.TAG) as MapFragment
                        f.setData(datos,actualLocation)
                        f.setUserVisibleHint(true)
                    }
                }
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun setData(datos: MutableList<Restaurante>, location: Location) {
        this.datos=datos
        this.actualLocation=location
    }

    override fun onFragmentDetached(tag: String) {
        supportFragmentManager?.removeFragment(tag = tag)
    }
    private fun checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                AlertDialog.Builder(this)
                        .setTitle("Se necesitan permisos de localización")
                        .setMessage("La aplicación necesita permisos de localización para poder funcionar")
                        .setCancelable(false)
                        .setPositiveButton("OK", DialogInterface.OnClickListener { _, _ ->
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
                        })
                        .create()
                        .show()

            } else ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_LOCATION_CODE)
        }
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            REQUEST_LOCATION_CODE -> {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        try {
                            supportFragmentManager.removeFragment(HomeFragment.TAG)
                            val newHomeFragment = HomeFragment.newInstance()
                            supportFragmentManager.addFragment(R.id.cl_root_view, newHomeFragment, HomeFragment.TAG)
                            newHomeFragment.getMainInterfaceData(this)
                        }catch (Ex:Exception){
                            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
                        }

                    }
                } else {
                    this.finish()
                }
                return
            }
        }
    }
    override fun blockUi() {

    }

    override fun unBlockUi() {

    }

    override fun onFragmentAttached() {
    }
}