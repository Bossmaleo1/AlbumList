package com.leboncointest.android.fake.datasource

import com.leboncointest.android.albums
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import com.leboncointest.android.data.repository.dataSource.album.AlbumRemoteDataSource
import com.leboncointest.android.util.Resource
import retrofit2.Response

class FakeAlbumRemoteDataSource : AlbumRemoteDataSource {

    var getAlbumsWasCalled = false
        private set
    override suspend fun getAlbums(): Response<List<Album>> {
        getAlbumsWasCalled = true
        return Response.success(albums)
    }
}