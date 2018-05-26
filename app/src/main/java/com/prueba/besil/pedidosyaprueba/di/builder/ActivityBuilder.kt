package com.prueba.besil.pedidosyaprueba.di.builder

import com.prueba.besil.pedidosyaprueba.ui.home.HomeFragmentModule
import com.prueba.besil.pedidosyaprueba.ui.home.view.HomeFragment
import com.prueba.besil.pedidosyaprueba.ui.home.view.MapFragment
import com.prueba.besil.pedidosyaprueba.ui.main.MainActivityModule
import com.prueba.besil.pedidosyaprueba.ui.main.View.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = [(HomeFragmentModule::class)])
    abstract fun bindMapFragment(): MapFragment
}