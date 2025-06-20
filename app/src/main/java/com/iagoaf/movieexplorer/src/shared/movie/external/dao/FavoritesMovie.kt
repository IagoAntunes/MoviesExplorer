package com.iagoaf.movieexplorer.src.shared.movie.external.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.iagoaf.movieexplorer.src.shared.movie.domain.entity.MovieEntity

@Dao
interface FavoritesMovieDao {
    @Query("SELECT * FROM favorites_movies")
    suspend fun getAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovie(movie: MovieEntity): Unit

    @Query("SELECT EXISTS(SELECT 1 FROM favorites_movies WHERE id = :id)")
    suspend fun isFavorite(id: Int): Boolean

    @Query("DELETE FROM favorites_movies WHERE id = :id")
    suspend fun removeMovieById(id: Int)
}
