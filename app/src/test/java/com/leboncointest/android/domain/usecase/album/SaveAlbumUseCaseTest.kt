package com.leboncointest.android.domain.usecase.album

import com.leboncointest.android.domain.repository.FakeAlbumRepository
import org.junit.Assert.*
import org.junit.Before

class SaveAlbumUseCaseTest {

    private lateinit var fakeAlbumRepository: FakeAlbumRepository

    @Before
    fun setUp() {
        fakeAlbumRepository = FakeAlbumRepository()
    }

}