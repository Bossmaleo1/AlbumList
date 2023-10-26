package com.leboncointest.android.presentation.di

import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.repository.dataSource.album.AlbumRemoteDataSource
import com.leboncointest.android.data.repository.dataSourceImpl.AlbumRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideAlbumRemoteDataSource(
        albumAPIService: AlbumAPIService
    ): AlbumRemoteDataSource {
        return AlbumRemoteDataSourceImpl(
                albumAPIService = albumAPIService
        )
    }
}