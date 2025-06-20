package com.iagoaf.movieexplorer.src.features.movieDetail.presentation

sealed class MovieDetailState{
    object Idle : MovieDetailState()
    object Loading : MovieDetailState()
    object Success : MovieDetailState()
    data class Error(val message: String) : MovieDetailState()
}