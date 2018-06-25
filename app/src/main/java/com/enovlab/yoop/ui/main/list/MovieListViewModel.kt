package com.enovlab.yoop.ui.main.list

import com.enovlab.yoop.BuildConfig
import com.enovlab.yoop.data.repository.MovieRepository
import com.enovlab.yoop.ui.base.state.StateViewModel
import com.enovlab.yoop.utils.ext.plusAssign
import com.enovlab.yoop.utils.ext.toCompletable
import timber.log.Timber
import javax.inject.Inject

class MovieListViewModel
@Inject constructor(private val movieRepository: MovieRepository
) : StateViewModel<MovieListView>() {

    internal var searchTerm: String? = null

    override fun start() {
        observeMovies()
        observeErrors()
        loadMovies()
    }

    internal fun refresh(search: String) {
        searchTerm = search
        loadMovies(true)
    }

    private fun observeErrors() {
        disposables += movieRepository.error
            .observeOn(schedulers.main)
            .subscribe {
                if (searchTerm?.isEmpty() == true) {
                    view?.showError(it)
                }
            }
    }

    private fun loadMovies(refresh: Boolean = false) {
        if (searchTerm?.isNotEmpty() == true) {
            when {
                refresh -> refresh { movieRepository.loadMovies(APIKEY, searchTerm).toCompletable() }
                else -> load {
                    movieRepository.loadMovies(APIKEY, searchTerm).toCompletable()
                }
            }
        }
    }

    private fun observeMovies() {
        disposables += movieRepository.observeMovies()
            .subscribe({
                //submit list of movies to view for updating
                view?.submitList(it)

                if (it.isEmpty()) {
                    view?.showEmptyList(true)
                    view?.showList(false)
                } else {
                    view?.showEmptyList(false)
                    view?.showList(true)
                }
            },
                {
                    Timber.e(it.message)
                })

    }

    companion object {
        private const val APIKEY = BuildConfig.API_KEY
    }

}