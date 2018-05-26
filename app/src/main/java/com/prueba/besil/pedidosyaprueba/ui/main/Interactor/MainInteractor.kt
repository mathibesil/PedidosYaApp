package com.prueba.besil.pedidosyaprueba.ui.main.Interactor

import com.prueba.besil.pedidosyaprueba.data.preferences.PreferenceHelper
import com.prueba.besil.pedidosyaprueba.ui.base.iteractor.BaseInteractor
import javax.inject.Inject

class MainInteractor @Inject internal constructor(preferenceHelper: PreferenceHelper) : BaseInteractor(preferenceHelper), MainMVPInteractor{

}