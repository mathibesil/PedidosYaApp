package com.prueba.besil.pedidosyaprueba.ui.main

import com.prueba.besil.pedidosyaprueba.ui.main.Interactor.MainInteractor
import com.prueba.besil.pedidosyaprueba.ui.main.Interactor.MainMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.main.Presenter.MainMVPPresenter
import com.prueba.besil.pedidosyaprueba.ui.main.Presenter.MainPresenter
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainMVPView
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(interactor: MainInteractor): MainMVPInteractor = interactor

    @Provides
    internal fun provideMainPresenter(presenter: MainPresenter<MainMVPView, MainMVPInteractor>)
            : MainMVPPresenter<MainMVPView, MainMVPInteractor> = presenter


}