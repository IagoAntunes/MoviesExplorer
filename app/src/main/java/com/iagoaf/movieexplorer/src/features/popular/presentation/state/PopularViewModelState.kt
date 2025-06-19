package com.iagoaf.movieexplorer.src.features.popular.presentation.state

import com.iagoaf.movieexplorer.src.shared.MovieModel

sealed class PopularViewModelState {
    object Loading : PopularViewModelState()
    data class Success(val movies: List<MovieModel>) : PopularViewModelState()
    data class Error(val message: String) : PopularViewModelState()
}