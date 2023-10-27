package com.leboncointest.android.presentation.di

import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideAlbumViewModelFactory(
        getRemoteAlbumUseCase: GetRemoteAlbumUseCase,
        saveAlbumUseCase: SaveAlbumUseCase,
        deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase,
        getLocalAlbumUseCase: GetLocalAlbumUseCase
    ) : AlbumViewModelFactory {
        return AlbumViewModelFactory(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )
    }
}