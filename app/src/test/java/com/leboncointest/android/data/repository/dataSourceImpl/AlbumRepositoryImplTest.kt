package com.leboncointest.android.data.repository.dataSourceImpl

import app.cash.turbine.test
import com.google.common.truth.Truth
import com.leboncointest.android.albums
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.fake.datasource.FakeAlbumLocalDataSource
import com.leboncointest.android.fake.datasource.FakeAlbumRemoteDataSource
import com.leboncointest.android.util.Resource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Response

@ExperimentalCoroutinesApi
class AlbumRepositoryImplTest {

    @Test
    fun `getAlbums() returns a result `() = runTest {
        // Arrange
        val albumRepository = AlbumRepositoryImpl(
            albumRemoteDataSource = FakeAlbumRemoteDataSource(),
            albumLocalDataSource = FakeAlbumLocalDataSource()
        )

        val expectedAlbum = albums

        // Act
        val result = albumRepository.getAlbums()

        // Assert
        assert(result is Resource.Success)
        assertEquals((result as Resource.Success).data, expectedAlbum)
    }

    @Test
    fun `we test if responseToResourceAlbums return true values `() = runTest {
        // Arrange
        val albumRepository = AlbumRepositoryImpl(
            albumRemoteDataSource = FakeAlbumRemoteDataSource(),
            albumLocalDataSource = FakeAlbumLocalDataSource()
        )

        val expectedSuccessAlbum = albums

        val responseSuccessAlbumList = albumRepository.responseToResourceAlbums(
            Response.success(albums)
        )

        val responseErrorAlbumList = albumRepository.responseToResourceAlbums(
            Response.error(500,ResponseBody.create(null,"Internal Error, Error 500"))
        )


        // Assert
        assert(responseSuccessAlbumList is Resource.Success)
        assertEquals((responseSuccessAlbumList as Resource.Success).data, expectedSuccessAlbum)
        //if we have an error
        assert(responseErrorAlbumList is Resource.Error)
    }

    @Test
    fun `we test if getLocalAlbums return true values `() = runTest {
        // Arrange
        val albumRepository = AlbumRepositoryImpl(
            albumRemoteDataSource = FakeAlbumRemoteDataSource(),
            albumLocalDataSource = FakeAlbumLocalDataSource()
        )

        val expectedAlbums = albumsRoom
        val albumLocalElement = albumRepository.getLocalAlbums()
        assert(albumLocalElement is Flow<List<AlbumRoom>>)
        albumLocalElement.collect {albumsRoom ->
            assertEquals(albumsRoom , expectedAlbums)
        }
    }

}