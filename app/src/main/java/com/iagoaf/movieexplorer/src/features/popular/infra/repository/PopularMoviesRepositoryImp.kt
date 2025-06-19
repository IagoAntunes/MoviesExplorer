package com.iagoaf.movieexplorer.src.features.popular.infra.repository

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.features.popular.domain.repository.IPopularMoviesRepository
import com.iagoaf.movieexplorer.src.features.popular.external.service.PopularMoviesService
import com.iagoaf.movieexplorer.src.shared.MovieModel
import javax.inject.Inject

class PopularMoviesRepositoryImp @Inject constructor(
    val popularMoviesService: PopularMoviesService
) : IPopularMoviesRepository {
    override suspend fun getPopularMovies(): BaseResult<List<MovieModel>> {
        val response = popularMoviesService.getPopularMovies()
        val body = response.body()
        return if (response.isSuccessful && body != null) {
            BaseResult.Success(
                data = body.results
            )
        } else {
            BaseResult.Error(
                message = response.errorBody()?.string() ?: "Unknown error"
            )
        }
    }

}