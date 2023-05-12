package com.example.moviedatabasebase.ui.movielist

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.moviedatabasebase.R
import com.example.moviedatabasebase.adapter.MovieListAdapter
import com.example.moviedatabasebase.data.model.Movie
import com.example.moviedatabasebase.data.model.Result
import com.example.moviedatabasebase.data.remote.NetworkResult
import com.example.moviedatabasebase.data.remote.Retrofit
import com.example.moviedatabasebase.databinding.ActivityMainBinding
import com.example.moviedatabasebase.repository.Repository
import com.example.moviedatabasebase.ui.base.MyViewModelFactory
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var adapter: MovieListAdapter
    lateinit var viewModel: MainViewModel
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       // val apiService = Retrofit.getApiService()
        //val repository = Repository(apiService)


        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //setupRecyclerView(movie.results)
        setupObserver()
        viewModel.getMovieList()
    }

    private fun setupObserver() {
        viewModel.response.observe(this){ respone ->
            when(respone){
                is NetworkResult.SUCCESS -> {
                    hideLoader()
                    respone.data?.results?.let { setupRecyclerView(it) }
                }
                is NetworkResult.FAILURE -> {
                    hideLoader()
                    Toast.makeText(this,respone.message,Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.LOADING -> {
                    showLoader()
                }
            }
        }


    }

    private fun setupRecyclerView(results: List<Result>) {
        adapter = MovieListAdapter(this)
        binding.rvMovieList.adapter = adapter
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)

/*        val gson = Gson()
        val json = readJsonFromRawResource(this,R.raw.response)
        val results = gson.fromJson(json,Movie::class.java)*/

        adapter.updateList(results)

    }

    private fun showLoader() {
        binding.rvMovieList.visibility = View.GONE
        binding.progressCircular.visibility = View.VISIBLE

    }

    private fun hideLoader() {
        binding.rvMovieList.visibility = View.VISIBLE
        binding.progressCircular.visibility = View.GONE
    }

    fun readJsonFromRawResource(context: Context, resourceId: Int): String {
        val jsonBuilder = StringBuilder()
        try {
            val inputStream = context.resources.openRawResource(resourceId)
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                jsonBuilder.append(line)
            }
            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return jsonBuilder.toString()
    }
}