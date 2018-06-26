package com.vishaan.movieapp

import com.vishaan.movieapp.inject.DaggerAppComponent
import com.squareup.leakcanary.LeakCanary
import com.zopim.android.sdk.api.ZopimChat
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import timber.log.Timber
import zendesk.core.Zendesk

class Application : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        if (!LeakCanary.isInAnalyzerProcess(this)) {
            LeakCanary.install(this)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}