package com.example.moviedatabase.data.repository

import com.example.moviedatabase.data.api.ApiService

class Repository constructor(private val apiService: ApiService) {
    suspend fun fetchQuotes() = apiService.fetchData()
}