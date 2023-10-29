package com.leboncointest.android.fake.datasource

import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAlbumLocalDataSource: AlbumLocalDataSource {
    override fun getLocalAlbums(): Flow<List<AlbumRoom>> {
        return flow {
            emit(albumsRoom)
        }
    }

    override suspend fun deleteAlbums() {
        // Delete
    }

    override suspend fun insertAlbum(album: AlbumRoom) {
        //save
    }

}