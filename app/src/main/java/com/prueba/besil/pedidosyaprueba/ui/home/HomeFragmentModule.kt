package com.prueba.besil.pedidosyaprueba.ui.home

import android.support.v7.widget.LinearLayoutManager
import com.prueba.besil.pedidosyaprueba.ui.home.interactor.HomeInteractor
import com.prueba.besil.pedidosyaprueba.ui.home.interactor.HomeMVPInteractor
import com.prueba.besil.pedidosyaprueba.ui.home.presenter.HomeMVPPresenter
import com.prueba.besil.pedidosyaprueba.ui.home.presenter.HomePresenter
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeFragment
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeMVPView
import dagger.Module
import dagger.Provides

@Module
class HomeFragmentModule {

    @Provides
    internal fun provideHomeInteractor(interactor: HomeInteractor): HomeMVPInteractor = interactor

    @Provides
    internal fun provideHomePresenter(presenter: HomePresenter<HomeMVPView, HomeMVPInteractor>)
            : HomeMVPPresenter<HomeMVPView, HomeMVPInteractor> = presenter

    @Provides
    internal fun provideLinearLayoutManager(fragment: HomeFragment): LinearLayoutManager = LinearLayoutManager(fragment.activity)

}