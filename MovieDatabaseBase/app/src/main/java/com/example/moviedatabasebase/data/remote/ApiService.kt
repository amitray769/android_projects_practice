package com.example.moviedatabasebase.data.remote

import com.example.moviedatabasebase.data.model.Movie
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovieList(@Query("api_key") apiKey : String) : Movie
}