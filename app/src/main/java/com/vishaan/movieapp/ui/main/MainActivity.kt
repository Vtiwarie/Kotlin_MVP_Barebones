package com.vishaan.movieapp.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.MenuItem
import com.vishaan.movieapp.R
import com.vishaan.movieapp.ui.base.state.StateActivity
import com.vishaan.movieapp.ui.main.detail.MovieDetailFragment
import com.vishaan.movieapp.ui.main.list.MovieListFragment

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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val fragment = supportFragmentManager.findFragmentById(CONTAINER)
        if (fragment is MovieDetailFragment) {
            return fragment.onItemSelected(item)
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val CONTAINER = R.id.container_main
    }
}
