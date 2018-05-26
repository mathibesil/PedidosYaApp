package com.prueba.besil.pedidosyaprueba.ui.main.View

import android.location.Location
import com.prueba.besil.pedidosyaprueba.data.network.DTO.Restaurante
import com.prueba.besil.pedidosyaprueba.ui.base.view.MVPView

interface MainMVPView : MVPView {
    fun setData(datos: MutableList<Restaurante>, location: Location)
}