package com.iagoaf.movieexplorer.src.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagoaf.movieexplorer.src.features.search.presentation.state.SearchState
import com.iagoaf.movieexplorer.src.shared.MovieModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    private val _state = MutableStateFlow<SearchState>(SearchState.Idle)
    val state: StateFlow<SearchState> = _state.asStateFlow()


    fun searchMovie(name: String) {
        viewModelScope.launch {
            _state.value = SearchState.Loading
            delay(2000)
            _state.value = SearchState.Success(emptyList())
        }
    }

}