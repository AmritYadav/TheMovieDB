package com.amydvdev.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amydvdev.domain.CoroutinesDispatcherProvider
import com.amydvdev.domain.Result
import com.amydvdev.domain.model.Movie
import com.amydvdev.domain.usecase.PopularMovieUseCase
import com.amydvdev.presentation.states.Resource
import com.amydvdev.presentation.states.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GetPopularMoviesViewModel @Inject constructor(
    private val useCase: PopularMovieUseCase,
    private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider
) : ViewModel() {

    private var _popularMovies = MutableLiveData<Resource<List<Movie>>?>()
    val popularMovies: LiveData<Resource<List<Movie>>?> = _popularMovies

    fun getPopularMovies() = viewModelScope.launch {
        _popularMovies.value = Resource(ResourceState.LOADING)

        val result = withContext(coroutinesDispatcherProvider.io) {
            return@withContext useCase.getPopularMovies()
        }

        when (result) {
            is Result.Success -> {
                _popularMovies.value = Resource(ResourceState.SUCCESS, data = result.data)
            }
            is Result.Error -> {
                _popularMovies.value = Resource(ResourceState.ERROR, errMsg = result.exception.message)
            }
        }
    }

    fun reset() {
        _popularMovies.value = null
    }

}