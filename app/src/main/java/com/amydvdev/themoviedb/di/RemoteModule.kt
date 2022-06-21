package com.amydvdev.themoviedb.di

import com.amydvdev.data.movies.repository.MovieRemote
import com.amydvdev.remote.impl.MovieRemoteImpl
import com.amydvdev.remote.services.MovieServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteModule {

    companion object {
        @Provides
        fun providesMovieService(retrofit: Retrofit): MovieServices {
            return retrofit.create(MovieServices::class.java)
        }
    }

    @Binds
    abstract fun bindsMovieRemote(movieRemoteImpl: MovieRemoteImpl): MovieRemote

}