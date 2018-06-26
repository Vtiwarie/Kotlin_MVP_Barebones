package com.vishaan.movieapp.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.vishaan.movieapp.data.dao.MovieDao
import com.vishaan.movieapp.data.entity.Movie

@Database(entities = [
    Movie::class
], version = 1)
@TypeConverters(AppTypeConverters::class)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}