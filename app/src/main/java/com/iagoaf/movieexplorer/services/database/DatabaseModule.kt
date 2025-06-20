package com.iagoaf.movieexplorer.services.database

import android.content.Context
import androidx.room.Room
import com.iagoaf.movieexplorer.src.shared.movie.external.dao.FavoritesMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "movie_explorer_db"
        ).build()
    }

    @Provides
    fun provideFavoritesMovieDao(db: AppDatabase): FavoritesMovieDao = db.favoritesMovieDao()
}