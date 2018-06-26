package com.vishaan.movieapp.inject

import android.arch.persistence.room.Room
import android.content.Context
import android.os.Debug
import com.vishaan.movieapp.data.CacheDatabase
import com.vishaan.movieapp.data.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): CacheDatabase {
        val builder = Room.databaseBuilder(context, CacheDatabase::class.java, "movies.db")
            .fallbackToDestructiveMigration()
        if (Debug.isDebuggerConnected()) {
            builder.allowMainThreadQueries()
        }
        return builder.build()
    }

    @Provides
    fun provideMovieDao(db: CacheDatabase): MovieDao = db.movieDao()
}
