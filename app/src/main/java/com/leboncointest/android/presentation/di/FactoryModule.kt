package com.leboncointest.android.presentation.di

import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModelFactory
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    fun provideAlbumViewModelFactory(
        getRemoteAlbumUseCase: GetRemoteAlbumUseCase,
        saveAlbumUseCase: SaveAlbumUseCase,
        deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase
    ) : AlbumViewModelFactory {
        return AlbumViewModelFactory(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase
        )
    }
}