package com.leboncointest.android.domain.usecase.album

import com.google.common.truth.Truth
import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DeleteLocalAlbumUseCaseTest {

    private lateinit var fakeAlbumRepository: FakeAlbumRepository
    private lateinit var deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase

    @Before
    fun setUp() {
        fakeAlbumRepository = FakeAlbumRepository()
    }


    @Test
    fun `we delete our local album`() = runTest {
        deleteLocalAlbumUseCase.execute()
    }
}