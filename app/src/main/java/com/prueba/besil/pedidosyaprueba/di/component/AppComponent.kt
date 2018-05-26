package com.prueba.besil.pedidosyaprueba.di.component

import android.app.Application
import com.prueba.besil.pedidosyaprueba.PedidosYaApp
import com.prueba.besil.pedidosyaprueba.di.builder.ActivityBuilder
import com.prueba.besil.pedidosyaprueba.di.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,AppModule::class,
        ActivityBuilder::class))

interface AppComponent {

    @Component.Builder
        interface Builder{
            @BindsInstance fun application(app: Application): Builder
            fun build(): AppComponent
    }

    fun inject(app: PedidosYaApp)
}