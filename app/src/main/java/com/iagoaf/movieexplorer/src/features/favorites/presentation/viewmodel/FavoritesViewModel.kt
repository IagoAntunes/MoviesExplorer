package com.iagoaf.movieexplorer.src.features.favorites.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.favorites.presentation.state.FavoritesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FavoritesViewModel : ViewModel() {
    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Idle)
    val state: StateFlow<FavoritesState> = _state.asStateFlow()

    init {
        _getFavorites()
    }

    private fun _getFavorites() {
        viewModelScope.launch {
            _state.value = FavoritesState.Loading


            _state.value = FavoritesState.Success(emptyList())
        }
    }
}