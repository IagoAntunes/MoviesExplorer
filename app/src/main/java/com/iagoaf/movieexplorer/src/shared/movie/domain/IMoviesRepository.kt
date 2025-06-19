package com.iagoaf.movieexplorer.src.shared.movie.domain

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.shared.MovieModel

interface IMoviesRepository {
    suspend fun getPopularMovies(): BaseResult<List<MovieModel>>
    suspend fun searchMovies(query: String, page: Int): BaseResult<List<MovieModel>>
}