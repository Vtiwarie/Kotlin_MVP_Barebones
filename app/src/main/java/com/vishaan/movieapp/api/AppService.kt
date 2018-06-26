package com.vishaan.movieapp.api

import com.vishaan.movieapp.data.entity.Movie
import com.vishaan.movieapp.data.entity.Search
import io.reactivex.Flowable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AppService {
    @GET("/")
    fun getMovies(@Query("apikey")apikey: String, @Query("s") s: String?): Flowable<Search>

    @GET("/")
    fun getMovie(@Query("apikey")apikey: String, @Query("i") id: String?): Single<Movie>
}
