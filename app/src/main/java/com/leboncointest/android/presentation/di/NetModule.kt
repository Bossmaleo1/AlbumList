package com.leboncointest.android.presentation.di

import com.leboncointest.android.BuildConfig
import com.leboncointest.android.data.apiService.AlbumAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetModule {

    /**
     * Here we inject our Product Api Service
     */
    @Singleton
    @Provides
    fun provideAlbumAPIService(retrofit: Retrofit): AlbumAPIService {
        return retrofit.create(AlbumAPIService::class.java)
    }

    /**
     * Here we inject Retrofit and OkHttp
     */
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
        }.build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL_DEV)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}