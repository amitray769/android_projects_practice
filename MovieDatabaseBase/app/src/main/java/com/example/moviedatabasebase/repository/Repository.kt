package com.example.moviedatabasebase.repository

import com.example.moviedatabasebase.data.local.MovieDao
import com.example.moviedatabasebase.data.remote.ApiService
import com.example.moviedatabasebase.data.model.Result
import javax.inject.Inject

class Repository @Inject constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao) {
    suspend fun getMovieList(apiKey : String) = apiService.getMovieList(apiKey)

    suspend fun saveMovie(result: Result) = movieDao.saveMovieList(result)

    fun getAllMovieList() = movieDao.getMovieList()
}