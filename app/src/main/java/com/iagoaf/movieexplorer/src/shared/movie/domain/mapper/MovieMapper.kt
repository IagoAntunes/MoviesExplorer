package com.iagoaf.movieexplorer.src.shared.movie.domain.mapper

import com.iagoaf.movieexplorer.src.shared.movie.domain.entity.MovieEntity
import com.iagoaf.movieexplorer.src.shared.movie.domain.model.MovieModel

object MovieMapper {

    fun mapMovieEntityToMovieModel(movieEntity: MovieEntity): MovieModel {
        return MovieModel(
            adult = movieEntity.adult,
            backdropPath = movieEntity.backdropPath,
            genreIds = emptyList(),
            id = movieEntity.id,
            originalLanguage = movieEntity.originalLanguage,
            originalTitle = movieEntity.originalTitle,
            overview = movieEntity.overview,
            popularity = movieEntity.popularity,
            posterPath = movieEntity.posterPath,
            releaseDate = movieEntity.releaseDate,
            title = movieEntity.title,
            video = movieEntity.video,
            voteAverage = movieEntity.voteAverage,
            voteCount = movieEntity.voteCount
        )
    }

    fun mapMovieModelToMovieEntity(movieModel: MovieModel): MovieEntity {
        return MovieEntity(
            adult = movieModel.adult,
            backdropPath = movieModel.backdropPath,
            id = movieModel.id,
            originalLanguage = movieModel.originalLanguage,
            originalTitle = movieModel.originalTitle,
            overview = movieModel.overview,
            popularity = movieModel.popularity,
            posterPath = movieModel.posterPath,
            releaseDate = movieModel.releaseDate,
            title = movieModel.title,
            video = movieModel.video,
            voteAverage = movieModel.voteAverage,
            voteCount = movieModel.voteCount
        )
    }

}