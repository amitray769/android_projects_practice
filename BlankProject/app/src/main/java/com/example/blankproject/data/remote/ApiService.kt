package com.example.blankproject.data.remote

import com.example.blankproject.data.models.PlanetDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/api/planets/")
    suspend fun getPlanets(@Query("page") page: Int): PlanetDetailsResponse


}