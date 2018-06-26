package com.vishaan.movieapp.data.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movies",
    indices = [
        (Index(value = ["id"], unique = true))
    ])
data class Movie(

    @PrimaryKey
    @SerializedName("imdbID")
    var id: String,

    @SerializedName("Title")
    var title: String?,

    @SerializedName("Plot")
    var plot: String?,

    @SerializedName("Poster")
    var defaultImage: String?

): BaseEntity()