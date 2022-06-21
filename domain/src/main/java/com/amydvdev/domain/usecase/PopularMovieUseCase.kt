package com.amydvdev.domain.usecase

import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import com.amydvdev.domain.respository.MovieRepository
import javax.inject.Inject

class PopularMovieUseCase @Inject constructor(private val movieRepository: MovieRepository) {

    suspend fun getPopularMovies() : Result<List<Movie>> {
        return movieRepository.getPopularMovies()
    }

}