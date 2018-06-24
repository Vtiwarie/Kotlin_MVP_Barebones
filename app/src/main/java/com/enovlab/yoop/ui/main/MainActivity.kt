package com.enovlab.yoop.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import com.enovlab.yoop.R
import com.enovlab.yoop.ui.base.state.StateActivity
import com.enovlab.yoop.ui.main.detail.MovieDetailFragment
import com.enovlab.yoop.ui.main.list.MovieListFragment

class MainActivity : StateActivity<MainView, MainViewModel, MainNavigator>(), MainView {
    override val navigatorClass = MainNavigator::class.java
    override val vmClass = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(MainViewModel::class.java)

        showMoviesListFragment()
    }

    override fun setupNavigation(navigator: MainNavigator) {
    }

    private fun showMoviesListFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(CONTAINER, MovieListFragment.newInstance())
            .commit()
    }

    private fun showMovieDetailFragment(movieId: Int) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .replace(CONTAINER, MovieDetailFragment.newInstance(movieId))
            .commit()
    }

    companion object {
        private const val CONTAINER = R.id.container_main
    }
}
