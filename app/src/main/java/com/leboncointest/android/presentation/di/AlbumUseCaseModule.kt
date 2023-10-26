package com.leboncointest.android.presentation.di

import com.leboncointest.android.domain.repository.AlbumRepository
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AlbumUseCaseModule {

    @Singleton
    @Provides
    fun provideGetRemoteAlbumsUseCase(
        albumRepository: AlbumRepository
    ): GetRemoteAlbumUseCase {
        return GetRemoteAlbumUseCase(
            albumRepository = albumRepository
        )
    }

    @Singleton
    @Provides
    fun provideLocalAlbumUseCase(
        albumRepository: AlbumRepository
    ): GetLocalAlbumUseCase {
        return GetLocalAlbumUseCase(
            albumRepository = albumRepository
        )
    }

    @Singleton
    @Provides
    fun provideSaveAlbumUseCase(
        albumRepository: AlbumRepository
    ) : SaveAlbumUseCase {
        return SaveAlbumUseCase(
            albumRepository = albumRepository
        )
    }


    fun provideDeleteLocalAlbum(
        albumRepository: AlbumRepository
    ) : DeleteLocalAlbumUseCase {

        return DeleteLocalAlbumUseCase(
            albumRepository = albumRepository
        )
    }
}