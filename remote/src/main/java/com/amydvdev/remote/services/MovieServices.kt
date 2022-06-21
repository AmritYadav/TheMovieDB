package com.amydvdev.remote.services

import com.amydvdev.domain.response.MovieResponse
import com.amydvdev.remote.utils.ApiConstants
import retrofit2.Response
import retrofit2.http.GET

interface MovieServices {

    @GET(ApiConstants.GET_POPULAR_MOVIE)
    suspend fun getPopularMovies(): Response<MovieResponse>

}