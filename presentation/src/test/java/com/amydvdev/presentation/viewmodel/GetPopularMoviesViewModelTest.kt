package com.amydvdev.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amydvdev.domain.usecase.PopularMovieUseCase
import com.amydvdev.presentation.factory.MovieDataFactory
import com.amydvdev.presentation.utils.MainCoroutineRule
import com.amydvdev.presentation.utils.provideFakeCoroutinesDispatcherProvider
import com.amydvdev.presentation.states.ResourceState
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@Suppress("OPT_IN_IS_NOT_ENABLED")
@OptIn(ExperimentalCoroutinesApi::class)
class GetPopularMoviesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val popularMoviesUseCase = mock<PopularMovieUseCase>()

    private lateinit var viewModel: GetPopularMoviesViewModel

    @Before
    fun setup() {
        viewModel = GetPopularMoviesViewModel(
            popularMoviesUseCase,
            provideFakeCoroutinesDispatcherProvider(
                mainCoroutineRule.testDispatcher
            )
        )
    }

    @Test
    fun testGetPopularMovies_withSuccess() = runBlocking {
        // Given that the movie service responds with success
        val result = MovieDataFactory.getSuccessResult()
        whenever(popularMoviesUseCase.getPopularMovies()).thenReturn(result)

        // When requesting the Popular Movies
        viewModel.getPopularMovies()

        // Then status the response is success
        assertEquals(ResourceState.SUCCESS, viewModel.popularMovies.value?.status)
        // Then the correct set of popular movies is returned
        assertEquals(result.data, viewModel.popularMovies.value?.data)
    }

    @Test
    fun testGetPopularMovies_whenRequestFailed() = runBlocking {
        // Given that the service responds with failure
        val result = MovieDataFactory.getErrorResult()
        whenever(popularMoviesUseCase.getPopularMovies()).thenReturn(result)

        // When getting the list of popular movies
        viewModel.getPopularMovies()

        // Then the response is not successful
        assertEquals(ResourceState.ERROR, viewModel.popularMovies.value?.status)
    }

}