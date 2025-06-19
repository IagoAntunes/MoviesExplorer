package com.iagoaf.movieexplorer.src.shared.movie.external.service

import com.iagoaf.movieexplorer.services.http.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitClient.instance

    @Provides
    @Singleton
    fun provideLinkService(retrofit: Retrofit): MoviesService {
        return retrofit.create(MoviesService::class.java)
    }
}