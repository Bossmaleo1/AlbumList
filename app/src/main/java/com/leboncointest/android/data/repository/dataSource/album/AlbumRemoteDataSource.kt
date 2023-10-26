package com.leboncointest.android.data.repository.dataSource.album

import com.leboncointest.android.data.model.dataRemote.response.Album
import retrofit2.Response

interface AlbumRemoteDataSource {

    suspend fun getAlbums(): Response<List<Album>>


}