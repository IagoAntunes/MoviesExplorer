package com.iagoaf.movieexplorer.src.features.favorites.presentation.state

import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel

sealed class FavoritesState{
    object Idle : FavoritesState()
    object Loading : FavoritesState()
    data class Success(val movies: List<MovieModel>) : FavoritesState()
    data class Error(val message: String) : FavoritesState()
}