package com.prueba.besil.pedidosyaprueba.ui.home.interactor

import com.prueba.besil.pedidosyaprueba.data.network.DTO.Datos
import com.prueba.besil.pedidosyaprueba.data.network.Token
import com.prueba.besil.pedidosyaprueba.ui.base.iteractor.MVPInteractor
import io.reactivex.Observable

interface HomeMVPInteractor : MVPInteractor {
    fun getToken(clientId:String,clienteSecret:String): Observable<Token>?
    fun setToken(token:String)
    fun getTokenLocal(): String
    fun getDatos(latitud:String,longitud:String,offset:Int): Observable<Datos>
}