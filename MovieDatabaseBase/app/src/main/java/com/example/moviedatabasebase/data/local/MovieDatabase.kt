package com.example.moviedatabasebase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviedatabasebase.data.model.Result

@Database(entities = [Result::class], version = 1, exportSchema = false)
abstract class MovieDatabase  : RoomDatabase(){
    abstract fun getMovieDao() : MovieDao
}