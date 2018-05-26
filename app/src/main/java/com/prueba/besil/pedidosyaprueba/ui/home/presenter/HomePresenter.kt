package com.prueba.besil.pedidosyaprueba.ui.home.presenter

import com.prueba.besil.pedidosyaprueba.data.network.util.NoConnectivityException
import com.prueba.besil.pedidosyaprueba.ui.base.presenter.BasePresenter
import com.prueba.besil.pedidosyaprueba.ui.home.interactor.HomeMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeMVPView
import io.reactivex.Observable
import org.json.JSONObject
import retrofit2.HttpException
import javax.inject.Inject

class HomePresenter <V: HomeMVPView, I: HomeMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V,I>(interactor), HomeMVPPresenter<V,I>{
    override fun getToken(clientId: String, clientSecret: String) {
        interactor?.getToken(clientId, clientSecret)
                ?.subscribe({
                    interactor?.setToken(it.token)
                }, { error : Throwable ->
                    if (error is java.net.SocketTimeoutException || error is java.net.ConnectException) getView()?.showMessage("No se pudo conectar con servidor")
                    if (error is NoConnectivityException) getView()?.showMessage("Sin conexión a internet")
                    try {
                        error as HttpException
                        val jObjError = JSONObject(error.response().errorBody()!!.string())
                        getView()?.showMessage(jObjError.getString("messages"))
                    } catch (e: Exception) {
                        getView()?.showMessage("Error al obtener datos.")
                    }
                    loadersOff()
                })
    }

    override fun getDatos(latitud: String, longitud: String, offset: Int) {
        if (offset > 0)
            getView()?.loadMoreProgress(true)
        else
            getView()?.loadProgress(true)
        interactor?.getDatos(latitud, longitud, offset)
                ?.subscribe({datos ->
                    getView()?.updateRestaurantes(datos)
                }, { error : Throwable ->
                if (error is java.net.SocketTimeoutException || error is java.net.ConnectException) getView()?.showMessage("No se pudo conectar con servidor")
                    if (error is NoConnectivityException) getView()?.showMessage("Sin conexión a internet")
                    try {
                        error as HttpException
                        val jObjError = JSONObject(error.response().errorBody()!!.string())
                        getView()?.showMessage(jObjError.getString("messages"))
                    } catch (e: Exception) {
                        getView()?.showMessage("Error al obtener datos.")
                    }
                    loadersOff()
                },{
                    loadersOff()
                }
                )
    }

    private fun loadersOff() {
        getView()?.loadMoreProgress(false)
        getView()?.loadProgress(false)
        getView()?.swipeRefreshOff()
    }
}