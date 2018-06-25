package com.enovlab.yoop.data.dao

import android.arch.persistence.room.*
import com.enovlab.yoop.data.entity.Movie
import com.enovlab.yoop.data.query.MovieQuery
import io.reactivex.Flowable


@Dao
abstract class MovieDao  {

    @Transaction
    @Query("SELECT * FROM movies LIMIT 100")
    abstract fun getMovies(): Flowable<List<MovieQuery>>

    @Transaction
    @Query("SELECT * FROM movies WHERE id = :id")
    abstract fun getMovie(id: String?): Flowable<MovieQuery>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun saveMovie(movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun saveMovieInternal(movie: Movie)

    @Transaction
    @Query("DELETE FROM movies")
    abstract fun clearMovies()

    @Transaction
    open fun saveMovies(movies: List<Movie>){
        movies.forEach{
            if(it != null && movies.isNotEmpty()) {
                saveMovieInternal(it)
            }
        }
    }
}
