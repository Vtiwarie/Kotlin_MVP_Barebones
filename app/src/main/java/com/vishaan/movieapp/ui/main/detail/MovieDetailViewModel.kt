package com.vishaan.movieapp.ui.main.detail

import com.vishaan.movieapp.BuildConfig
import com.vishaan.movieapp.data.repository.MovieRepository
import com.vishaan.movieapp.ui.base.state.StateViewModel
import com.vishaan.movieapp.utils.ext.plusAssign
import com.vishaan.movieapp.utils.ext.toCompletable
import timber.log.Timber
import javax.inject.Inject

class MovieDetailViewModel
@Inject constructor(private val movieRepository: MovieRepository) : StateViewModel<MovieDetailView>() {

    var id: String? = null

    override fun start() {
        observeMovie(id)
        load { movieRepository.loadMovie(APIKEY, id).toCompletable() }
    }

    private fun observeMovie(id: String?) {
        disposables += movieRepository.observeMovie(id)
            .subscribe({
                if (it != null) {
                    if (it.defaultImage != null) {
                        view?.showMovieImage(it.defaultImage)
                    } else {
                        view?.showNoImage()
                    }
                    view?.showMovieTitle(it.title)
                    view?.showMoviePlot(it.plot)
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