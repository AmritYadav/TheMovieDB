package com.amydvdev.themoviedb.di

import com.amydvdev.data.movies.impl.MovieRepositoryImpl
import com.amydvdev.domain.respository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

}