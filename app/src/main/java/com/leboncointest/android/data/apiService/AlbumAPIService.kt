package com.leboncointest.android.data.apiService

import com.leboncointest.android.data.model.dataRemote.response.Album
import retrofit2.Response

interface AlbumAPIService {

   suspend fun getAlbums() : Response<Album>

}