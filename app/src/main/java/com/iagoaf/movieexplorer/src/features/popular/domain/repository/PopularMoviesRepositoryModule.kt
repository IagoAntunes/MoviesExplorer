package com.iagoaf.movieexplorer.src.features.popular.domain.repository

import com.iagoaf.movieexplorer.src.features.popular.infra.repository.PopularMoviesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class PopularMoviesRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindPopularMoviesRepository(
        impl: PopularMoviesRepositoryImp
    ): IPopularMoviesRepository
}
