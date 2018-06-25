package com.enovlab.yoop.inject

import android.content.Context
import com.enovlab.yoop.Application
import com.enovlab.yoop.data.manager.*
import com.enovlab.yoop.utils.RxSchedulers
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

    @Provides
    @Singleton
    fun provideFileManager(manager: FileManagerImpl): FileManager = manager

    @Provides
    @Singleton
    fun provideKeyStoreManager(manager: KeyStoreManagerImpl): KeyStoreManager = manager
}
