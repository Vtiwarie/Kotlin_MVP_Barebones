package com.vishaan.movieapp.inject

import com.vishaan.movieapp.Application
import com.vishaan.movieapp.inject.api.ApiModule
import com.vishaan.movieapp.inject.viewmodel.ViewModelBuilder
import com.vishaan.movieapp.ui.main.MainBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    AppModule::class,
    DatabaseModule::class,
    NetworkModule::class,
    ApiModule::class,
    ViewModelBuilder::class,
    MainBuilder::class
))
interface AppComponent : AndroidInjector<Application> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<Application>()
}
