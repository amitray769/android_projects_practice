package com.example.moviedatabase.data.api

import com.example.moviedatabase.data.model.Movies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/now_playing?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1")
    suspend fun fetchData(): Response<List<Movies>>
}