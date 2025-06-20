package com.iagoaf.movieexplorer.src.shared.movie.domain.repository

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel

interface IMoviesRepository {
    suspend fun getPopularMovies(): BaseResult<List<MovieModel>>
    suspend fun searchMovies(query: String, page: Int): BaseResult<List<MovieModel>>
    suspend fun loadFavoritesMovies(): BaseResult<List<MovieModel>>
    suspend fun addMovieToFavorites(movie: MovieModel): BaseResult<Unit>
    suspend fun verifyIfMovieIsFavorite(id: Int): BaseResult<Boolean>
    suspend fun removeMovieFromFavorites(id: Int): BaseResult<Unit>
}