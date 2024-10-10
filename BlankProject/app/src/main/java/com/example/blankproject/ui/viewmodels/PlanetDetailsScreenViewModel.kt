package com.example.blankproject.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.blankproject.data.PlanetPagingSource
import com.example.blankproject.data.models.Result
import com.example.blankproject.data.remote.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlanetDetailsScreenViewModel : ViewModel() {

    private val _planetDetails = MutableStateFlow(emptyList<Result>())
    val planetDetails: StateFlow<List<Result>> = _planetDetails

    val planetPagingFlow = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PlanetPagingSource(ApiClient.apiService)
    }.flow.cachedIn(viewModelScope)



    fun getPlanetDetails(){
        viewModelScope.launch {
            val response = ApiClient.apiService.getPlanets(1)
            _planetDetails.value = response.results
        }
    }

    fun getPlanetDetailsByPage(url : String){
    //find id from the planetPagingFlow

    }


    companion object{
        const val PAGE_SIZE = 10
    }



}