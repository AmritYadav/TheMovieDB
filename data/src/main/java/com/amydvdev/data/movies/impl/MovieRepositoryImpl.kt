package com.amydvdev.data.movies.impl

import com.amydvdev.data.movies.repository.MovieRemote
import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import com.amydvdev.domain.respository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl  @Inject constructor(
    private val remote: MovieRemote,
) : MovieRepository {

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return remote.getPopularMovies()
    }
}