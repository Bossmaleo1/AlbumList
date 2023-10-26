package com.leboncointest.android.data.apiService

import com.leboncointest.android.data.model.dataRemote.response.Album
import retrofit2.Response
import retrofit2.http.GET


interface AlbumAPIService {

   @GET("/img/shared/technical-test.json")
   suspend fun getAlbums() : Response<List<Album>>

}