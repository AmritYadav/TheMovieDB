package com.amydvdev.themoviedb.utils

object Helpers {

    private const val BASE_POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    private const val BASE_BACKDROP_PATH = "https://image.tmdb.org/t/p/w780"

    fun buildPosterPath(path: String) : String {
        return BASE_POSTER_PATH + path
    }

    fun buildBackdropPath(path: String) : String {
        return BASE_BACKDROP_PATH + path
    }
}