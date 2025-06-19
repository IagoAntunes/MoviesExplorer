package com.iagoaf.movieexplorer.src.features.popular.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.popular.domain.repository.IPopularMoviesRepository
import com.iagoaf.movieexplorer.src.features.popular.presentation.state.PopularViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(
    private val repository: IPopularMoviesRepository
) : ViewModel() {

    private val _state = MutableStateFlow<PopularViewModelState>(PopularViewModelState.Loading)
    val state: StateFlow<PopularViewModelState> = _state.asStateFlow()

    init {
        getPopularMovies()
    }

    private fun getPopularMovies() {
        viewModelScope.launch {
            _state.value = PopularViewModelState.Loading
            val result = repository.getPopularMovies()
            result.onSuccess { movies ->
                _state.value = PopularViewModelState.Success(movies)
            }
            result.onError { error ->
                _state.value = PopularViewModelState.Error(error)
            }
        }

    }


}





