package com.vishaan.movieapp.data.repository

import com.vishaan.movieapp.api.AppService
import com.vishaan.movieapp.data.dao.MovieDao
import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.data.entity.Search
import com.vishaan.movieapp.data.query.MovieQuery
import com.vishaan.movieapp.utils.RxSchedulers
import io.reactivex.Flowable
import io.reactivex.Single
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class MovieRepository
@Inject constructor(private val appService: AppService,
                    private val movieDao: MovieDao,
                    private val schedulers: RxSchedulers) {

    var error = BehaviorSubject.create<String>()

    fun observeMovies(): Flowable<List<Movie>> {
        return movieDao.getMovies()
            .subscribeOn(schedulers.disk)
            .observeOn(schedulers.disk)
            .map { it.map(MovieQuery::toMovie) }
            .observeOn(schedulers.main)
            .distinctUntilChanged()
    }

    fun observeMovie(id: String?): Flowable<Movie> {
        return movieDao.getMovie(id)
            .subscribeOn(schedulers.disk)
            .observeOn(schedulers.main)
            .map(MovieQuery::toMovie)
            .observeOn(schedulers.main)
            .distinctUntilChanged()
    }

    fun loadMovies(apikey: String, search: String?): Flowable<Search> {
        return appService.getMovies(apikey, search)
            .subscribeOn(schedulers.network)
            .observeOn(schedulers.disk)
            .doOnNext {
                when {
                    it.error != null && it.error!!.isNotEmpty() -> {
                        error.onNext(it.error!!)
                    }
                    else -> {
                        movieDao.clearMovies()
                        movieDao.saveMovies(it.movies)
                    }
                }
            }
    }

    fun loadMovie(apikey: String, id: String?): Single<Movie> {
        return appService.getMovie(apikey, id)
            .subscribeOn(schedulers.network)
            .observeOn(schedulers.disk)
            .doOnSuccess {
                movieDao.saveMovie(it)
            }
    }
}
