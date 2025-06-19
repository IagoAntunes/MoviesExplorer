package com.iagoaf.movieexplorer.src.shared.movie.domain

import com.iagoaf.movieexplorer.src.shared.movie.infra.repository.MoviesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MoviesRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        impl: MoviesRepositoryImp
    ): IMoviesRepository
}
