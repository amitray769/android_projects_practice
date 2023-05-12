package com.example.moviedatabasebase.di

import android.content.Context
import androidx.room.Room
import com.example.moviedatabasebase.data.local.MovieDao
import com.example.moviedatabasebase.data.local.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun provideMovieDao(movieDatabase: MovieDatabase) : MovieDao{
        return movieDatabase.getMovieDao()
    }

    @Provides
    fun provideMovieDatabase(@ApplicationContext context: Context) : MovieDatabase{
        return Room.databaseBuilder(context,MovieDatabase::class.java,"MovieDatabase").build()
    }
}