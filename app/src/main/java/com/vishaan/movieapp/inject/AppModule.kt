package com.vishaan.movieapp.inject

import android.content.Context
import com.enovlab.yoop.data.manager.DataMangerImpl
import com.vishaan.movieapp.Application
import com.vishaan.movieapp.data.manager.*
import com.vishaan.movieapp.utils.RxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(app: Application): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideRxSchedulers() = RxSchedulers()

    @Provides
    @Singleton
    fun provideAppPreferences(appPreferences: AppPreferencesImpl): AppPreferences = appPreferences

    @Provides
    @Singleton
    fun provideDataManager(manager: DataMangerImpl): DataManager = manager
}
