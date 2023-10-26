package com.leboncointest.android.domain.usecase

import com.leboncointest.android.domain.repository.AlbumRepository

class DeleteLocalAlbumUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute() = albumRepository.deleteAlbums()
}