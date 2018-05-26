package com.prueba.besil.pedidosyaprueba.ui.main.Presenter

import com.prueba.besil.pedidosyaprueba.ui.base.presenter.MVPPresenter
import com.prueba.besil.pedidosyaprueba.ui.main.Interactor.MainMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainMVPView

interface MainMVPPresenter<V:MainMVPView, I:MainMVPInteractor> : MVPPresenter<V,I>{

}