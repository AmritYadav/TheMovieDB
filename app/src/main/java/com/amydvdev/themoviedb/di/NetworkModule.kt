package com.amydvdev.themoviedb.di

import com.amydvdev.themoviedb.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


private const val TIME_OUT = 20L

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun makeHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

    @Singleton
    @Provides
    fun makeClientAuthInterceptor() = ClientAuthInterceptor(BuildConfig.TMDB_API_KEY)

    @Provides
    fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        clientAuthInterceptor: ClientAuthInterceptor
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(clientAuthInterceptor)
        .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
        .readTimeout(TIME_OUT, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofitService(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    inner class ClientAuthInterceptor(private val apiKey: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("api_key", apiKey)
                .build()

            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)

            val request: Request = requestBuilder.build()
            return chain.proceed(request)
        }
    }
}