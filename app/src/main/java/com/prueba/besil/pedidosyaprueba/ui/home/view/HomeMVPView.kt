package com.prueba.besil.pedidosyaprueba.ui.home.view

import com.prueba.besil.pedidosyaprueba.data.network.DTO.Datos
import com.prueba.besil.pedidosyaprueba.ui.base.view.MVPView
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainMVPView

interface HomeMVPView : MVPView {
    fun openLoginActivity()
    fun updateRestaurantes(datos: Datos)
    fun loadMoreProgress(enabled: Boolean)
    fun swipeRefreshOff()
    fun getDatos()
    fun loadProgress(enabled: Boolean)
    fun firstLoadRestaurantes()
    fun scrollToTop()
    fun getMainInterfaceData(mainInterface: MainMVPView)
}