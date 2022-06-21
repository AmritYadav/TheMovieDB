package com.amydvdev.remote.impl

import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import com.amydvdev.domain.response.MovieResponse
import com.amydvdev.remote.factory.MovieDataFactory
import com.amydvdev.remote.impl.MovieRemoteImpl
import com.amydvdev.remote.services.MovieServices
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class MovieRemoteImplTest {

    private val service: MovieServices = mock()
    private val movieRemoteImpl = MovieRemoteImpl(service)

    @Test
    fun getPopularMovies_withSuccess() = runBlocking {
        // Given that the movie service responds with success
        val response = MovieDataFactory.getMovieResponse()
        whenever(service.getPopularMovies()).thenReturn(Response.success(response))

        // When requesting the Popular Movies
        val result = movieRemoteImpl.getPopularMovies()

        // Then there's one request to the service
        verify(service).getPopularMovies()
        // Then the correct set of popular movies is returned
        assertEquals(Result.Success(response.results), result)
    }

    @Test
    fun getPopularMovies_whenRequestFailed() = runBlocking {

        val errorResponseBody = "Error".toResponseBody("".toMediaTypeOrNull())

        // Given that the service responds with failure
        val result = Response.error<MovieResponse>(
            400,
            errorResponseBody
        )
        whenever(service.getPopularMovies()).thenReturn(result)

        // When getting the list of popular movies
        val response = movieRemoteImpl.getPopularMovies()

        // Then the response is not successful
        assertTrue(response is Result.Error)
    }

}