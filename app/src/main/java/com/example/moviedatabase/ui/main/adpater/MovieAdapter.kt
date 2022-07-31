package com.example.moviedatabase.ui.main.adpater

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedatabase.data.model.Result
import com.example.moviedatabase.databinding.MovieListItemBinding
import com.example.moviedatabase.utils.IMAGE_URL_PATH


class MovieAdapter(private val onItemClick: (movie: Result) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    var movieList = mutableListOf<Result>()

    fun setMovies(movies: List<Result>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieListItemBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val movie = movieList[position]

        holder.binding.tvTitle.text = movie.title
        holder.binding.tvOverview.text = movie.overview
        Glide.with(holder.itemView.context).load(IMAGE_URL_PATH+movie.poster_path).into(holder.binding.ivPoster)
        holder.itemClicked(movie)

    }

    override fun getItemCount(): Int {
        return movieList.size
    }
}

class MainViewHolder(val binding: MovieListItemBinding,private val onItemClick: (movie: Result) -> Unit ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
    var movie = Result
    override fun onClick(v: View?) {
        v?.setOnClickListener{
            onItemClick
        }
    }

    fun itemClicked( movie: Result){
     this.movie = movie
    }


}