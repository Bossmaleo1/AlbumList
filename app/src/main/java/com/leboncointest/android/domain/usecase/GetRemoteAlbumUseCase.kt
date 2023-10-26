package com.leboncointest.android.domain.usecase

import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.domain.repository.AlbumRepository
import com.leboncointest.android.util.Resource

class GetRemoteAlbumUseCase(private val albumRepository: AlbumRepository)  {
    suspend fun execute(): Resource<List<Album>> {
        return albumRepository.getAlbums()
    }
}