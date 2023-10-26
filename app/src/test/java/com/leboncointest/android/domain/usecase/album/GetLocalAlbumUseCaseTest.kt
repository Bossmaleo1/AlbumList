package com.leboncointest.android.domain.usecase.album

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before

import org.junit.Test

@ExperimentalCoroutinesApi
class GetLocalAlbumUseCaseTest {

    private lateinit var fakeAlbumRepository: FakeAlbumRepository
    private lateinit var getLocalAlbumUseCase: GetLocalAlbumUseCase

    @Before
    fun setUp() {
        fakeAlbumRepository = FakeAlbumRepository()
        getLocalAlbumUseCase = GetLocalAlbumUseCase(albumRepository = fakeAlbumRepository)
    }

    @Test
    fun `retrieve all local albums`() = runTest {
        //we test if our product list retrieve good list
        getLocalAlbumUseCase.execute().test {
            val localAlbums = expectMostRecentItem()
            Truth.assertThat(localAlbums?.size).isEqualTo(4)
            Truth.assertThat(localAlbums?.get(0)?.albumId).isEqualTo(1)
            Truth.assertThat(localAlbums?.get(0)?.thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
            Truth.assertThat(localAlbums?.get(0)?.id).isEqualTo(1)
            Truth.assertThat(localAlbums?.get(0)?.title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
            Truth.assertThat(localAlbums?.get(0)?.url).isEqualTo("https://via.placeholder.com/600/92c952")
        }

    }
}