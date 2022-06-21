package com.amydvdev.themoviedb.ui.movies.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amydvdev.domain.model.Movie

@SuppressLint("NotifyDataSetChanged")
class PopularMoviesAdapter(
    private val callback: (Movie) -> Unit
) : RecyclerView.Adapter<PopularMoviesViewHolder>() {

    var items: List<Movie> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMoviesViewHolder {
        return PopularMoviesViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.bind(items[position], callback)
    }

    override fun getItemCount(): Int = items.size
}