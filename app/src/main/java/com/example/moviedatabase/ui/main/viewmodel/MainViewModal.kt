package com.example.moviedatabase.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.moviedatabase.data.model.Movies
import com.example.moviedatabase.data.repository.Repository
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: Repository) : ViewModel() {

    val errorMessage = MutableLiveData<String>()
    val moviesList = MutableLiveData<List<Movies>>()
    var job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }
    val loading = MutableLiveData(false)

    fun getMoviesList() {
        //Creating a coroutine for doing the network operation in different thread instead of main thread
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            loading.postValue(true)
            val response = mainRepository.fetchQuotes()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    moviesList.postValue(response.body())
                    loading.value = false
                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}