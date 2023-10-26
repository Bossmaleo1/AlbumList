package com.leboncointest.android.domain.usecase

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.domain.repository.AlbumRepository

class SaveAlbumUseCase(private val albumRepository: AlbumRepository) {
    suspend fun execute(album: AlbumRoom) = albumRepository.insertAlbum(album = album)
}