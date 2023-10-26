package com.leboncointest.android.presentation.di

import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalDataModule {

    @Singleton
    @Provides
    fun provideAlbumLocalDataSource(albumDAO: AlbumDAO): AlbumLocalDataSource {
        return AlbumLocalDataSourceImpl(albumDAO)
    }

}