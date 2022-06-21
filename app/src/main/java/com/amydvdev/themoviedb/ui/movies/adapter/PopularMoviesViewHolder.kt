package com.amydvdev.themoviedb.ui.movies.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amydvdev.domain.model.Movie
import com.amydvdev.themoviedb.databinding.ItemPopularMovieBinding
import com.amydvdev.themoviedb.utils.Helpers
import com.amydvdev.themoviedb.utils.loadImage

class PopularMoviesViewHolder(
    private val binding: ItemPopularMovieBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, callback: (Movie) -> Unit) {
        binding.moviePoster.loadImage(Helpers.buildPosterPath(movie.posterPath))
        itemView.setOnClickListener {
            callback.invoke(movie)
        }
    }

    companion object {
        fun create(parent: ViewGroup): PopularMoviesViewHolder {
            return PopularMoviesViewHolder(
                ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

}