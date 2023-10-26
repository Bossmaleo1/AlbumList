package com.leboncointest.android.domain.usecase.album

import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import org.junit.Assert.*
import org.junit.Before

class SaveAlbumUseCaseTest {

    private lateinit var fakeAlbumRepository: FakeAlbumRepository
    private lateinit var saveAlbumUseCase: SaveAlbumUseCase

    @Before
    fun setUp() {
        fakeAlbumRepository = FakeAlbumRepository()
        saveAlbumUseCase = SaveAlbumUseCase(fakeAlbumRepository)
    }

}