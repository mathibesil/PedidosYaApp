package com.prueba.besil.pedidosyaprueba.data.preferences

import com.prueba.besil.pedidosyaprueba.util.AppConstant

interface PreferenceHelper {
    fun setToken(texto: String?)
    fun getToken(): String
}