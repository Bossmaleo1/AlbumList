package com.leboncointest.android.presentation.di

import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import com.leboncointest.android.data.repository.dataSource.album.AlbumRemoteDataSource
import com.leboncointest.android.data.repository.AlbumRepositoryImpl
import com.leboncointest.android.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    /**
     * Here we inject Album Repository Implementation
     */
    @Singleton
    @Provides
    fun provideAlbumRepository(
        albumLocalDataSource: AlbumLocalDataSource,
        albumRemoteDataSource: AlbumRemoteDataSource
    ): AlbumRepository {
        return AlbumRepositoryImpl(
            albumLocalDataSource = albumLocalDataSource,
            albumRemoteDataSource = albumRemoteDataSource
        )
    }
}