package com.amydvdev.domain.response

import com.amydvdev.domain.model.Movie

data class MovieResponse(
    val results: List<Movie>
)
