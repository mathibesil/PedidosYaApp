package com.prueba.besil.pedidosyaprueba.ui.base.iteractor

import com.prueba.besil.pedidosyaprueba.data.preferences.PreferenceHelper
import com.prueba.besil.pedidosyaprueba.util.AppConstant

/**
 */
open class BaseInteractor(): MVPInteractor {
    protected lateinit var preferenceHelper: PreferenceHelper

    constructor(preferenceHelper: PreferenceHelper) : this() {
        this.preferenceHelper = preferenceHelper
    }
}