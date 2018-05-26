package com.prueba.besil.pedidosyaprueba.data.network

import com.prueba.besil.pedidosyaprueba.data.network.DTO.Datos
import com.prueba.besil.pedidosyaprueba.util.AppConstant.BASEURL
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PedidosYaService {

    @GET(BASEURL + "tokens")
    fun getToken(@Query("clientId") clientID: String, @Query("clientSecret") clientSecret: String): Observable<Token>

    @GET(BASEURL + "search/restaurants")
    fun getDatos(@Query("point") point: String,
                 @Query("country") country: Int,
                 @Query("offset") offset: Int,
                 @Query("max") max: Int,
                 @Query("fields") fields: String
    ): Observable<Datos>
}