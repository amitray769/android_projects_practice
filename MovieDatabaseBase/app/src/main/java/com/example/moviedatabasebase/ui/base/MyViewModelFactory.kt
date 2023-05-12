package com.example.moviedatabasebase.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviedatabasebase.repository.Repository
import com.example.moviedatabasebase.ui.movielist.MainViewModel

class MyViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}