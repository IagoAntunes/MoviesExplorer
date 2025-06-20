package com.iagoaf.movieexplorer.src.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.favorites.presentation.state.FavoritesState
import com.iagoaf.movieexplorer.src.shared.movie.infra.repository.MoviesRepositoryImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    val moviesRepositoryImp: MoviesRepositoryImp
) : ViewModel() {
    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Idle)
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    init {
        getFavorites()
    }

    fun getFavorites() {
        viewModelScope.launch {
            _state.value = FavoritesState.Loading
            val result = moviesRepositoryImp.loadFavoritesMovies()
            result.onSuccess { movies, _ ->
                _state.value = FavoritesState.Success(movies)
            }
        }
    }
}