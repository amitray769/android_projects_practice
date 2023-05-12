package com.example.moviedatabasebase.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedatabasebase.data.model.Result
import com.example.moviedatabasebase.databinding.ItemMovieBinding
import com.example.moviedatabasebase.util.IMAGE_BASE_PATH

class MovieListAdapter(private val context: Context) :
    RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {
    private var result : List<Result>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return result?.size ?: 0
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        result?.get(position)?.let { holder.setData(it) }
    }

     fun updateList(result : List<Result>) {
        this.result = result
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(private val itemMovieBinding: ItemMovieBinding) :
        RecyclerView.ViewHolder(itemMovieBinding.root) {
         val t = itemMovieBinding
            fun setData(result : Result){
               itemMovieBinding.tvTitle.text = result.title
               itemMovieBinding.tvDescription.text = result.overview
                Glide.with(context).load(IMAGE_BASE_PATH+result.poster_path).into(itemMovieBinding.posterImage)
            }

    }

}