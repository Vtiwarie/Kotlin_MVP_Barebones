package com.enovlab.yoop.data.repository

import com.enovlab.yoop.api.AppService
import com.enovlab.yoop.data.dao.MovieDao
import com.enovlab.yoop.data.entity.Movie
import com.enovlab.yoop.data.entity.Search
import com.enovlab.yoop.data.query.MovieQuery
import com.enovlab.yoop.utils.RxSchedulers
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class MovieRepository
@Inject constructor(private val appService: AppService,
                    private val movieDao: MovieDao,
                    private val schedulers: RxSchedulers) {

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

    fun loadMovies(apikey: String, search: String): Flowable<Search> {
        return appService.getMovies(apikey, search)
            .subscribeOn(schedulers.network)
            .observeOn(schedulers.disk)
            .doOnNext {
                movieDao.clearMovies()
                movieDao.saveMovies(it.movies)
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
