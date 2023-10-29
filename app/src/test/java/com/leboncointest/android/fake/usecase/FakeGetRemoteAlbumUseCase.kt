package com.leboncointest.android.fake.usecase

import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.util.Resource

class FakeGetRemoteAlbumUseCase(private  val fakeAlbumRepository: FakeAlbumRepository) {
    suspend fun execute(): Resource<List<Album>> {
        return fakeAlbumRepository.getAlbums()
    }
}