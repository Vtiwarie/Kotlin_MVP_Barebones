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

    override fun start() {
        observeMovies()
        load { movieRepository.loadMovies(APIKEY, "Jurassic World").toCompletable() }
    }

    private fun observeMovies() {
        disposables += movieRepository.observeMovies()
            .subscribe({
                //submit list of movies to view for updating
                view?.submitList(it)
            },
                {
                    Timber.d(it.message)
                })

    }

    companion object {
        private const val APIKEY = BuildConfig.API_KEY
    }

}