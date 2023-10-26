package com.leboncointest.android.domain.usecase.album

import com.google.common.truth.Truth
import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetRemoteAlbumUseCaseTest {

    private lateinit var getRemoteAlbumUseCase : GetRemoteAlbumUseCase
    private lateinit var fakeAlbumRepository: FakeAlbumRepository

    @Before
    fun setUp() {
        fakeAlbumRepository = FakeAlbumRepository()
        getRemoteAlbumUseCase = GetRemoteAlbumUseCase(
            albumRepository = fakeAlbumRepository
        )
    }

    /**
     * we test if we retrieve product list
     */
    @Test
    fun `we retrieve our albums list`() = runTest {
        val albums = getRemoteAlbumUseCase.execute().data
        //we test our album size
        Truth.assertThat(albums?.size).isEqualTo(4)
        Truth.assertThat(albums?.get(0)?.albumId).isEqualTo(1)
        Truth.assertThat(albums?.get(0)?.thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
        Truth.assertThat(albums?.get(0)?.id).isEqualTo(1)
        Truth.assertThat(albums?.get(0)?.title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
        Truth.assertThat(albums?.get(0)?.url).isEqualTo("https://via.placeholder.com/600/92c952")
    }

}