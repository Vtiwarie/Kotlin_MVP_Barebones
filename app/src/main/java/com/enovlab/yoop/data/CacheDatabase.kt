package com.enovlab.yoop.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import com.enovlab.yoop.data.dao.MovieDao
import com.enovlab.yoop.data.entity.Movie

@Database(entities = [
    Movie::class
], version = 1)
@TypeConverters(AppTypeConverters::class)
abstract class CacheDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}