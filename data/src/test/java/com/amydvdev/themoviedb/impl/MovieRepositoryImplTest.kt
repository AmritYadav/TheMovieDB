package com.amydvdev.themoviedb.impl

import com.amydvdev.data.movies.impl.MovieRepositoryImpl
import com.amydvdev.data.movies.repository.MovieRemote
import com.amydvdev.domain.Result
import com.amydvdev.themoviedb.factory.MovieDataFactory
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MovieRepositoryImplTest {

    private val movieRemote = mock<MovieRemote>()

    private val movieRepositoryImpl = MovieRepositoryImpl(movieRemote)

    @Test
    fun testPopularMoviesListSuccess() = runBlocking {
        // Given that the remote movie service responds with success
        val successResult = MovieDataFactory.getSuccessResult()
        whenever(movieRemote.getPopularMovies()).thenReturn(successResult)

        // When requesting the Popular Movies
        val result = movieRepositoryImpl.getPopularMovies()
        // Then there's one request to the remote
        verify(movieRemote).getPopularMovies()
        // Then the result is success
        assertTrue(result is Result.Success)
        // Then the correct set of Popular Movies is returned
        assertEquals((result as Result.Success).data, successResult.data)
    }

    @Test
    fun testPopularMoviesListFailure() = runBlocking {
        // Given that the remote movie service responds with failure
        val errorResult = MovieDataFactory.getErrorResult()
        whenever(movieRemote.getPopularMovies()).thenReturn(errorResult)

        // When requesting the Popular Movies
        val result = movieRepositoryImpl.getPopularMovies()
        // Then there's one request to the remote
        verify(movieRemote).getPopularMovies()
        // Then the result is error
        assertTrue(result is Result.Error)
    }

}