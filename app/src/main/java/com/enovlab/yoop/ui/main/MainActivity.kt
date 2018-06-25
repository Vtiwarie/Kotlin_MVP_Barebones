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

        navigateToMoviesListFragment()
    }

    override fun setupNavigation(navigator: MainNavigator) {
        navigator.navigateToMovieList.observeNavigation { navigateToMoviesListFragment() }
        navigator.navigateToMovieDetails.observeNavigation { navigateToMovieDetailFragment(it) }
    }

    private fun navigateToMoviesListFragment() {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .replace(CONTAINER, MovieListFragment.newInstance())
            .commit()
    }

    private fun navigateToMovieDetailFragment(movieId: String?) {
        supportFragmentManager.beginTransaction()
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .addToBackStack(null)
            .replace(CONTAINER, MovieDetailFragment.newInstance(movieId))
            .commit()
    }

    companion object {
        private const val CONTAINER = R.id.container_main
    }
}
