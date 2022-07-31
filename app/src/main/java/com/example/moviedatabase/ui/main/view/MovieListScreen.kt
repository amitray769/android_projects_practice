package com.example.moviedatabase.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.moviedatabase.data.api.Retrofit
import com.example.moviedatabase.data.model.Result
import com.example.moviedatabase.data.repository.Repository
import com.example.moviedatabase.databinding.ActivityMainBinding
import com.example.moviedatabase.ui.base.MyViewModelFactory
import com.example.moviedatabase.ui.main.adpater.MovieAdapter
import com.example.moviedatabase.ui.main.viewmodel.MainViewModel

class MovieListScreen : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private val adapter = MovieAdapter{ position -> onItemClick(position)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofitService = Retrofit.getInstance()
        val mainRepository = Repository(retrofitService)
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(mainRepository)
        ).get(MainViewModel::class.java)
        initViews()
    }

    private fun initViews() {
        binding.movieRecyclerview.adapter = adapter

        viewModel.moviesList.observe(this) {
            adapter.setMovies(it[0].results)
        }

        viewModel.errorMessage.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if (it) {
                binding.progressDialog.visibility = View.VISIBLE
            } else {
                binding.progressDialog.visibility = View.GONE
            }
        })

        viewModel.getMoviesList()
    }

    private fun onItemClick(movie : Result) {
      showMovieDetailsScreen(movie)
    }

    private fun showMovieDetailsScreen(movie : Result){
        val intent = Intent(this, MovieDetailsScreen::class.java)
        val bundle = Bundle()
        bundle.putParcelable("Movie",movie)
        intent.putExtra("MovieBundle",bundle)
        startActivity(intent)
    }

}
