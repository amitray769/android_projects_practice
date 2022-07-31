package com.example.moviedatabase.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.moviedatabase.R
import com.example.moviedatabase.data.model.Result
import com.example.moviedatabase.databinding.ActivityMainBinding
import com.example.moviedatabase.databinding.ActivityMovieDetailsScreenBinding
import com.example.moviedatabase.utils.IMAGE_URL_PATH

class MovieDetailsScreen : AppCompatActivity() {

    private lateinit var binding : ActivityMovieDetailsScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setView()
    }

   private fun setView(){
       val bundle = intent.getBundleExtra("MovieBundle")
       val movie =  bundle.getParcelable<ParcelObjectName>("Movie") as Result

       //Will set all the data here : due to time constraints not able to set all the view into data here.
       binding.tvPopularity = movie.popularity
       binding.tvDetails = movie.overview
       Glide.with(this).load(IMAGE_URL_PATH +movie.poster_path).into(binding.ivPoster)
   }

}