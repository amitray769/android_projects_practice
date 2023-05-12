package com.example.moviedatabasebase.data.remote

import com.example.moviedatabasebase.util.BASE_URL
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private  var apiService: ApiService? = null
    private val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiService(): ApiService {
        if (apiService == null){
            apiService =  retrofit.create(ApiService::class.java)
        }
        return apiService!!
    }
}