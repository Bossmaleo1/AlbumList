package com.leboncointest.android.domain.usecase.fake

import com.leboncointest.android.domain.repository.FakeAlbumRepository

class FakeDeleteLocalAlbumUseCase(private  val fakeAlbumRepository: FakeAlbumRepository) {
    suspend fun execute() = fakeAlbumRepository.deleteAlbums()
}