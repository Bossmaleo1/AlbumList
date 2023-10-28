package com.leboncointest.android.domain.usecase.fake

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.domain.repository.FakeAlbumRepository
import kotlinx.coroutines.flow.Flow

class FakeGetLocalAlbumUseCase(private  val fakeAlbumRepository: FakeAlbumRepository) {
    fun execute(): Flow<List<AlbumRoom>> {
        return fakeAlbumRepository.getLocalAlbums()
    }
}