package com.amydvdev.data.movies.repository

import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie

interface MovieRemote {

    suspend fun getPopularMovies(): Result<List<Movie>>

}