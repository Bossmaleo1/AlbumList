package com.leboncointest.android.domain.repository

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.util.Resource
import kotlinx.coroutines.flow.Flow

interface AlbumRepository {

    suspend fun getAlbums(): Resource<List<Album>>

    fun getLocalAlbums(): Flow<List<AlbumRoom>>

    suspend fun deleteAlbums()

    suspend fun insertAlbum(album: AlbumRoom)


}