package com.iagoaf.movieexplorer.src.features.search.presentation.state

import com.iagoaf.movieexplorer.src.shared.MovieModel

sealed class SearchState {
    object Idle : SearchState()
    object Loading : SearchState()
    data class Success(val movies: List<MovieModel>) :
        SearchState()

    data class Error(val message: String) : SearchState()
}