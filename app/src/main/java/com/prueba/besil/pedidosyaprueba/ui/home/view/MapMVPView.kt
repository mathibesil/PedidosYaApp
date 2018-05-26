package com.prueba.besil.pedidosyaprueba.ui.home.view

import android.location.Location
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Restaurante

interface MapMVPView {
    fun setData(datos: List<Restaurante>, actualLocation: Location)
    fun setUserVisibleHint(isVisibleToUser: Boolean)
    fun updateRestaurantes(datos: List<Restaurante>)
}
