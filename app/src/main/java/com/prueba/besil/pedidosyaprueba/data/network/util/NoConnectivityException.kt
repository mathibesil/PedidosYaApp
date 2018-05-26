package com.prueba.besil.pedidosyaprueba.data.network.util

import java.io.IOException

class NoConnectivityException : IOException() {
    companion object {
        fun getMessage(): String {
            return "No connectivity exception"
        }
    }
}