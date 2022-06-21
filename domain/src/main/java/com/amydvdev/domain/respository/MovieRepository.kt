package com.amydvdev.domain.respository

import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): Result<List<Movie>>

}