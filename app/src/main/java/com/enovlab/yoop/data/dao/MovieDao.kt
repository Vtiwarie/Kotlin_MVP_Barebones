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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun saveMovieInternal(movie: Movie)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    open fun saveMovies(movies: List<Movie>){
        movies.forEach{
            saveMovieInternal(it)
        }
    }
}
