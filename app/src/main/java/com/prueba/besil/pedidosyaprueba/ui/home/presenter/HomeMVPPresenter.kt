package com.prueba.besil.pedidosyaprueba.ui.home.presenter

import com.prueba.besil.pedidosyaprueba.ui.base.presenter.MVPPresenter
import com.prueba.besil.pedidosyaprueba.ui.home.interactor.HomeMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeMVPView


interface HomeMVPPresenter<V: HomeMVPView,I:HomeMVPInteractor> : MVPPresenter<V,I>{
    fun getToken(clientId:String, clientSecret:String)
    fun getDatos(latitud:String,longitud:String,offset:Int)
}