package com.prueba.besil.pedidosyaprueba.ui.base.presenter

import com.prueba.besil.pedidosyaprueba.ui.base.iteractor.MVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.base.view.MVPView

/**
 */
interface MVPPresenter<V : MVPView, I : MVPInteractor> {

    fun onAttach(view: V?)

    fun onDetach()

    fun getView(): V?

}