package com.prueba.besil.pedidosyaprueba.data.network.DTO

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Restaurante(@SerializedName("id") @Expose var id: String,
                       @SerializedName("name") @Expose var name: String,
                       @SerializedName("allCategories") @Expose var allCategories: String,
                       @SerializedName("coordinates") @Expose var coordinates: String,
                       @SerializedName("deliveryTimeMinMinutes") @Expose var deliveryTimeMinMinutes: String,
                       @SerializedName("deliveryTimeMaxMinutes") @Expose var deliveryTimeMaxMinutes: String,
                       @SerializedName("ratingScore") @Expose var ratingScore: String)