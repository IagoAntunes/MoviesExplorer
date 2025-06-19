package com.iagoaf.movieexplorer.src.shared.movie.infra.repository

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.shared.MovieModel
import com.iagoaf.movieexplorer.src.shared.movie.domain.IMoviesRepository
import com.iagoaf.movieexplorer.src.shared.movie.external.service.MoviesService
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    val moviesService: MoviesService
) : IMoviesRepository {
    override suspend fun getPopularMovies(): BaseResult<List<MovieModel>> {
        val response = moviesService.getPopularMovies()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            BaseResult.Success(
                data = body.results,
                totalPages = body.totalPages
            )
        } else {
            BaseResult.Error(
                message = response.errorBody()?.string() ?: "Unknown error"
            )
        }
    }

    override suspend fun searchMovies(query: String, page: Int): BaseResult<List<MovieModel>> {
        val response = moviesService.searchMovies(query, page)
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            BaseResult.Success(
                data = body.results,
                totalPages = body.totalPages
            )
        } else {
            BaseResult.Error(
                message = response.errorBody()?.string() ?: "Unknown error"
            )
        }
    }

}