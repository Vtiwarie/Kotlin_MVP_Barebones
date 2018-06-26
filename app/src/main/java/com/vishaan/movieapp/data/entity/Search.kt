package com.vishaan.movieapp.data.entity

import android.arch.persistence.room.Embedded
import com.google.gson.annotations.SerializedName

data class Search (

    @Embedded
    @SerializedName("Search")
    var movies: List<Movie>
): BaseEntity()