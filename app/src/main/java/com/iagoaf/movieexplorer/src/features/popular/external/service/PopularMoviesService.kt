package com.iagoaf.movieexplorer.src.features.popular.external.service

import com.iagoaf.movieexplorer.core.response.BaseResponse
import com.iagoaf.movieexplorer.src.shared.MovieModel
import retrofit2.Response
import retrofit2.http.GET

interface PopularMoviesService {

    companion object {
        const val BASE_URL = "movie/popular"
    }

    @GET(BASE_URL)
    suspend fun getPopularMovies(): Response<BaseResponse<List<MovieModel>>>

}