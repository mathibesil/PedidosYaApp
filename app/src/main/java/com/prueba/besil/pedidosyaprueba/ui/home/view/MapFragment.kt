package com.prueba.besil.pedidosyaprueba.ui.home.view


import android.Manifest
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.prueba.besil.pedidosyaprueba.R
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Restaurante
import com.prueba.besil.pedidosyaprueba.ui.base.view.BaseFragment


class MapFragment : BaseFragment(), MapMVPView {
    //region vars
    lateinit var mapFragment: SupportMapFragment
    lateinit var datos: List<Restaurante>
    private var REQUEST_LOCATION_CODE = 101
    private var mLocation: Location? = null
    //endregion
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }
    override fun setUp() {
        mapFragment = SupportMapFragment.newInstance()
        // R.id.map is a FrameLayout, not a Fragment
        childFragmentManager.beginTransaction().replace(R.id.map, mapFragment).commit()
        updateRestaurantes(datos)
    }

    companion object {
        internal val TAG = "MapFragment"
        fun newInstance(): MapFragment = MapFragment()
    }

    override fun setData(datos: List<Restaurante>, actualLocation: Location) {
        this.datos = datos
        this.mLocation = actualLocation
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            updateRestaurantes(datos)
        }
    }

    override fun updateRestaurantes(datos: List<Restaurante>) {
        mapFragment.getMapAsync(OnMapReadyCallback { googleMap ->
            googleMap.clear()
            datos.forEach({
                val latLng = LatLng(it.coordinates.split(",")[0].toDouble(), it.coordinates.split(",")[1].toDouble())
                googleMap.addMarker(MarkerOptions().position(latLng).title(it.name))
            })

            val latLng = LatLng(mLocation?.latitude!!, mLocation?.longitude!!)
            googleMap.addMarker(MarkerOptions().position(latLng).title("Mi Ubicación").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13F))
        })
    }



    //region unUsed
    override fun showMessage(texto: String) {
    }

    override fun blockUi() {
    }

    override fun unBlockUi() {
    }
    //endregion
}