package com.prueba.besil.pedidosyaprueba.ui.home.interactor

import com.prueba.besil.pedidosyaprueba.data.network.DTO.Datos
import com.prueba.besil.pedidosyaprueba.data.network.PedidosYaService
import com.prueba.besil.pedidosyaprueba.data.network.Token
import com.prueba.besil.pedidosyaprueba.data.preferences.PreferenceHelper
import com.prueba.besil.pedidosyaprueba.ui.base.iteractor.BaseInteractor
import com.prueba.besil.pedidosyaprueba.util.AppConstant
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class HomeInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper) : BaseInteractor(preferenceHelper), HomeMVPInteractor {
    @Inject
    lateinit var retrofit: Retrofit

    override fun getToken(clientId: String, clienteSecret: String): Observable<Token>? {
        return retrofit.create(PedidosYaService::class.java).getToken(clientId,clienteSecret).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
    override fun setToken(token: String) {
        preferenceHelper.setToken(token)
    }
    override fun getTokenLocal() = preferenceHelper.getToken()
    override fun getDatos(latitud:String,longitud:String,offset:Int): Observable<Datos> {
        return retrofit.create(PedidosYaService::class.java).getDatos("$latitud,$longitud",1,offset,20, AppConstant.FIELDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }
}