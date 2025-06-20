package com.iagoaf.movieexplorer.src.shared.movie.infra.repository

import com.iagoaf.movieexplorer.core.result.BaseResult
import com.iagoaf.movieexplorer.src.shared.movie.domain.mapper.MovieMapper
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import com.iagoaf.movieexplorer.src.shared.movie.domain.repository.IMoviesRepository
import com.iagoaf.movieexplorer.src.shared.movie.external.dao.FavoritesMovieDao
import com.iagoaf.movieexplorer.src.shared.movie.external.service.MoviesService
import javax.inject.Inject

class MoviesRepositoryImp @Inject constructor(
    val moviesService: MoviesService,
    val favoritesMovieDao: FavoritesMovieDao
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

    override suspend fun loadFavoritesMovies(): BaseResult<List<MovieModel>> {
        val result = favoritesMovieDao.getAll()
        return BaseResult.Success(
            data = result.map { MovieMapper.mapMovieEntityToMovieModel(it) },
            totalPages = 1,
        )
    }

    override suspend fun addMovieToFavorites(movie: MovieModel): BaseResult<Unit> {
        val movieEntity = MovieMapper.mapMovieModelToMovieEntity(movie)
        val result = favoritesMovieDao.addMovie(movieEntity)
        return BaseResult.Success(
            data = Unit,
            totalPages = 1,
        )
    }

    override suspend fun verifyIfMovieIsFavorite(id: Int): BaseResult<Boolean> {
        val result = favoritesMovieDao.isFavorite(id)
        val isFavorite = result
        return BaseResult.Success(
            data = isFavorite,
            totalPages = 1,
        )
    }

    override suspend fun removeMovieFromFavorites(id: Int): BaseResult<Unit> {
        favoritesMovieDao.removeMovieById(id)
        return BaseResult.Success(
            data = Unit,
            totalPages = 1,
        )
    }

}