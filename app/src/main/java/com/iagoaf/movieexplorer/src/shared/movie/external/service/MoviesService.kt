package com.iagoaf.movieexplorer.src.shared.movie.external.service

import com.iagoaf.movieexplorer.core.response.BaseResponse
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    companion object {
        const val BASE_URL = "movie/"
        const val MOVIE = "movie"
        const val POPULAR = "popular"
        const val SEARCH = "search"
    }

    @GET("$BASE_URL$POPULAR")
    suspend fun getPopularMovies(): Response<BaseResponse<List<MovieModel>>>


    @GET("$SEARCH/$MOVIE")
    suspend fun searchMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<BaseResponse<List<MovieModel>>>

}