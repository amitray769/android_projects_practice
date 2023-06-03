package com.example.starwarsblaster.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.starwarsblaster.ui.star_wars_blaster.TournamentViewModel

class TournamentViewModelFactory(val context : Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TournamentViewModel(context) as T
    }
}