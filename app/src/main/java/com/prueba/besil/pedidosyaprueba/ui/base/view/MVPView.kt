package com.prueba.besil.pedidosyaprueba.ui.base.view

interface MVPView {
    fun showProgress()
    fun hideProgress()
    fun showMessage(texto: String)
    fun blockUi()
    fun unBlockUi()
}
