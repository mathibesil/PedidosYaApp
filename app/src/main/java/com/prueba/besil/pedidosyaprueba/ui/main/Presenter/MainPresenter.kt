package com.prueba.besil.pedidosyaprueba.ui.main.Presenter

import com.prueba.besil.pedidosyaprueba.ui.base.presenter.BasePresenter
import com.prueba.besil.pedidosyaprueba.ui.main.Interactor.MainMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainMVPView
import javax.inject.Inject

class MainPresenter<V : MainMVPView, I : MainMVPInteractor> @Inject internal constructor(interactor: I) : BasePresenter<V, I>(interactor), MainMVPPresenter<V, I> {

}