package com.prueba.besil.pedidosyaprueba.data.network.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Datos(@SerializedName("total") @Expose var total: Int = 0, @SerializedName("data") @Expose var data: List<Restaurante>)