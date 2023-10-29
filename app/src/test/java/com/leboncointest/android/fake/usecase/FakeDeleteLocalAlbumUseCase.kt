package com.leboncointest.android.fake.usecase

import com.leboncointest.android.domain.repository.FakeAlbumRepository

class FakeDeleteLocalAlbumUseCase(private  val fakeAlbumRepository: FakeAlbumRepository) {
    suspend fun execute() = fakeAlbumRepository.deleteAlbums()
}