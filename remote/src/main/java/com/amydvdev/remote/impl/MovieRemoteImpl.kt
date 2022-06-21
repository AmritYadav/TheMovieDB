package com.amydvdev.remote.impl

import com.amydvdev.data.movies.repository.MovieRemote
import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import com.amydvdev.remote.BuildConfig
import com.amydvdev.remote.services.MovieServices
import com.amydvdev.remote.utils.safeApiCall
import java.io.IOException
import javax.inject.Inject

class MovieRemoteImpl @Inject constructor(private val movieServices: MovieServices): MovieRemote {

    override suspend fun getPopularMovies(): Result<List<Movie>> =
        safeApiCall(
            call = { requestPopularMovies() },
            errorMessage = "Something went wrong, Please try again"
        )

    private suspend fun requestPopularMovies(): Result<List<Movie>> {
        val response = movieServices.getPopularMovies()
        if (response.isSuccessful) {
            response.body()?.let {
                return Result.Success(it.results)
            }
        }
        return Result.Error(IOException("Throw IOException"))
    }

}