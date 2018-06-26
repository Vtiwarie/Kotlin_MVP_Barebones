package com.vishaan.movieapp.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.os.Bundle
import android.view.View
import com.vishaan.movieapp.BuildConfig
import com.vishaan.movieapp.data.manager.AppPreferencesImpl
import com.vishaan.movieapp.utils.RxAwareObserver
import com.vishaan.movieapp.utils.AppContextWrapper
import com.vishaan.movieapp.utils.ext.hideKeyboard
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

abstract class BaseActivity<N : ViewModel> : DaggerAppCompatActivity(), BaseView {

    protected abstract fun setupNavigation(navigator: N)

    protected abstract val navigatorClass: Class<N>
    @Inject protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private val navigationObserver by lazy { RxAwareObserver(lifecycle) }
    protected lateinit var navigator: N

    override fun attachBaseContext(newBase: Context) {
        val preferences = AppPreferencesImpl(newBase)
        super.attachBaseContext(AppContextWrapper.wrap(newBase, preferences.locale))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigator = ViewModelProvider(this, viewModelFactory).get(navigatorClass)
        setupNavigation(navigator)
    }

    override fun hideKeyboard() {
        findViewById<View>(android.R.id.content)?.hideKeyboard()
    }

    override fun onBackPressed() {
        navigateBack()
    }

    protected open fun navigateBack(finish: Boolean? = null) = when {
        finish == true || supportFragmentManager.backStackEntryCount == 1 -> supportFinishAfterTransition()
        else -> super.onBackPressed()
    }

    protected fun <T> PublishSubject<T>.observeNavigation(action: (T) -> Unit) {
        navigationObserver.observe(subscribe({ action(it) }, {}))
    }
}