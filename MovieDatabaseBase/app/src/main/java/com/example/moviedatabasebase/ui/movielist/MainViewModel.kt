package com.example.moviedatabasebase.ui.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviedatabasebase.data.model.Movie
import com.example.moviedatabasebase.data.remote.NetworkResult
import com.example.moviedatabasebase.repository.Repository
import com.example.moviedatabasebase.util.API_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject
import com.example.moviedatabasebase.data.model.Result

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private var _response = MutableLiveData<NetworkResult<Movie>>()
    private var moviedata : LiveData<List<Result>> = repository.getAllMovieList()
    val response: LiveData<NetworkResult<Movie>>
        get() = _response
    private var job: Job? = null

    fun getMovieList() {
        _response.postValue(NetworkResult.LOADING())
        job = CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = repository.getMovieList(API_KEY)
                if (response.results != null){
                    _response.postValue(NetworkResult.SUCCESS(response))
                    saveToDB(response.results)
                } else{
                    _response.postValue(NetworkResult.FAILURE(message = "some error occurred"))
                }
            } catch (e : java.lang.Exception){
                _response.postValue(NetworkResult.FAILURE(message = "some exception occurred"))

            }

        }


    }

    private fun saveToDB(results : List<Result>) {
       // viewModelScope.launch {
            CoroutineScope(Dispatchers.IO).launch{
                for (result in results){
                    repository.saveMovie(result)
                }
            }
      //  }
    }


    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}