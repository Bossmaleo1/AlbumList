package com.leboncointest.android.data.repository.dataSource.album

import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import kotlinx.coroutines.flow.Flow

interface AlbumLocalDataSource {

    fun getLocalAlbums(): Flow<List<AlbumRoom>>

    suspend fun deleteAlbums()

    suspend fun insertAlbum(album: AlbumRoom)

}