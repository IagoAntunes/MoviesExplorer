package com.iagoaf.movieexplorer.services.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.iagoaf.movieexplorer.src.shared.movie.domain.entity.MovieEntity
import com.iagoaf.movieexplorer.src.shared.movie.external.dao.FavoritesMovieDao

@Database(entities = [MovieEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritesMovieDao(): FavoritesMovieDao
}