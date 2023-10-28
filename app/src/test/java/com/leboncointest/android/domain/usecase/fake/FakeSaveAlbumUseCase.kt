package com.leboncointest.android.domain.usecase.fake

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.domain.repository.FakeAlbumRepository

class FakeSaveAlbumUseCase(private  val fakeAlbumRepository: FakeAlbumRepository) {
    suspend fun execute(album: AlbumRoom) = fakeAlbumRepository.insertAlbum(album = album)

}