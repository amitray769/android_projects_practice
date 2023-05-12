package com.example.moviedatabasebase.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.moviedatabasebase.data.model.Result

@Dao
interface MovieDao {
    @Insert
    suspend fun saveMovieList(movieList : Result)

    @Update
    suspend fun updateMovieList(movieList : Result)

    @Query("SELECT * FROM Result")
    fun getMovieList() : LiveData<List<Result>>
}