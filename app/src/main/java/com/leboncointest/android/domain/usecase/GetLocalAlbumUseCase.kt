package com.leboncointest.android.domain.usecase

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow

class GetLocalAlbumUseCase(private val albumRepository: AlbumRepository) {
      fun execute(): Flow<List<AlbumRoom>> {
        return albumRepository.getLocalAlbums()
    }
}