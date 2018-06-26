package com.vishaan.movieapp.data.query

import android.arch.persistence.room.Embedded
import com.vishaan.movieapp.data.entity.Movie

class MovieQuery {

    @Embedded
    lateinit var movie: Movie

    fun toMovie(): Movie {
        if (movie.defaultImage != null && (movie.defaultImage!!.isEmpty() || movie.defaultImage!!.equals("n/a", true))) {
            movie.defaultImage = null
        }
        return movie
    }
}