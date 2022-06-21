package com.amydvdev.presentation.factory

import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import java.io.IOException
import kotlin.random.Random

object MovieDataFactory {

    fun getSuccessResult() = Result.Success(
        data = listOf(
            getMovie(),
        )
    )

    fun getErrorResult() = Result.Error(
        exception = IOException("Something went wrong.")
    )

    private fun getMovie() = Movie(
        id = Random.nextInt(),
        title = "Sonic the Hedgehog 2",
        overview = "After settling in Green Hills, Sonic is eager to prove he has what it takes to be a true hero.",
        posterPath = "/6DrHO1jr3qVrViUO6s6kFiAGM7.jpg",
        backdropPath = "/egoyMDLqCxzjnSrWOz50uLlJWmD.jpg",
        voteAverage = 7.7f,
        releaseDate = "2022-03-30"
    )

}