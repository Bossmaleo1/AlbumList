package com.leboncointest.android.domain.repository

import com.leboncointest.android.albums
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.domain.repository.AlbumRepository
import com.leboncointest.android.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAlbumRepository : AlbumRepository {

    private var shouldRetrieveAlbumListNetworkError = false
    val fakeAlbumListResult = listOf<Album>()

    override suspend fun getAlbums(): Resource<List<Album>> {
        return if(shouldRetrieveAlbumListNetworkError) {
            Resource.Error("Error", null)
        } else {
            Resource.Success(albums)
        }
    }

    override fun getLocalAlbums(): Flow<List<AlbumRoom>> {
       return flow {
           emit(albumsRoom)
       }
    }

    override suspend fun deleteAlbums() {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlbum(album: AlbumRoom) {
        TODO("Not yet implemented")
    }


}