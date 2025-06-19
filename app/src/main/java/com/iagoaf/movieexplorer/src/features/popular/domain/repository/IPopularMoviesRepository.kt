package com.iagoaf.movieexplorer.src.features.popular.domain.repository

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.shared.MovieModel

interface IPopularMoviesRepository {
    suspend fun getPopularMovies(): BaseResult<List<MovieModel>>
}