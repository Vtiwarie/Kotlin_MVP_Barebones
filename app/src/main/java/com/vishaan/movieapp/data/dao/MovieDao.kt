package com.vishaan.movieapp.data.dao

import android.arch.persistence.room.*
import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.data.query.MovieQuery
import io.reactivex.Flowable


@Dao
abstract class MovieDao {

    @Transaction
    @Query("SELECT * FROM movies ORDER BY title ASC LIMIT 100")
    abstract fun getMovies(): Flowable<List<MovieQuery>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun getMovie(id: String?): Flowable<MovieQuery>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun saveMovieInternal(movie: Movie)

    @Query("DELETE FROM movies WHERE id NOT IN(:ids)")
    protected abstract fun deleteIrrelevantMovies(ids: List<String>?)

    @Transaction
    @Query("DELETE FROM movies")
    abstract fun clearMovies()

    @Transaction
    open fun saveMovies(movies: List<Movie>? = null) {
        syncMovies(movies)
    }

    private fun syncMovies(movies: List<Movie>? = null) {
        val ids = movies?.map { it.id }

        deleteIrrelevantMovies(ids)

        movies?.forEach {
            saveMovieInternal(it)
        }
    }
}
