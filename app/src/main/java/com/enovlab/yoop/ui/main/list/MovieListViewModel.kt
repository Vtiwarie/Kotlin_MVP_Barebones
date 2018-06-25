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

    //Initially load movies with "Jurassic World" search terms. This is configurable upon dev's discretion
    override fun start() {
        observeMovies()
        load { movieRepository.loadMovies(APIKEY, "").toCompletable() }
    }

    internal fun refresh(search: String) {
        refresh { movieRepository.loadMovies(APIKEY, search).toCompletable() }
    }

    private fun observeMovies() {
        disposables += movieRepository.observeMovies()
            .subscribe({
                //submit list of movies to view for updating
                if (it.isEmpty()) {
                    view?.showEmptyList(true)
                    view?.showList(false)
                } else {
                    view?.showEmptyList(false)
                    view?.submitList(it)
                    view?.showList(true)
                }
            },
                {
                    Timber.d(it.message)
                })

    }

    companion object {
        private const val APIKEY = BuildConfig.API_KEY
    }

}