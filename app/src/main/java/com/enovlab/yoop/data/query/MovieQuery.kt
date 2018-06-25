package com.enovlab.yoop.data.query

import android.arch.persistence.room.Embedded
import com.enovlab.yoop.data.entity.Movie

class MovieQuery {

    @Embedded
    lateinit var movie: Movie

    fun toMovie(): Movie {
        return movie
    }
}