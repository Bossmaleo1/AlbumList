package com.leboncointest.android.data.repository.dataSourceImpl.album

import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.data.repository.dataSource.album.AlbumRemoteDataSource
import retrofit2.Response

class AlbumRemoteDataSource(
    private val albumAPIService: AlbumAPIService
) : AlbumRemoteDataSource {
    override suspend fun getAlbums(): Response<List<Album>> {
        return albumAPIService.getAlbums()
    }


}