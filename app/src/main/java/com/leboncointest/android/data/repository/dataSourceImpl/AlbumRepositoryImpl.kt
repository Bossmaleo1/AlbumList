package com.leboncointest.android.data.repository.dataSourceImpl

import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import kotlinx.coroutines.flow.Flow

class AlbumRepositoryImpl(
    private val albumDAO: AlbumDAO
) : AlbumLocalDataSource {
    override fun getLocalAlbums(): Flow<List<AlbumRoom>> {
        return albumDAO.getAllAlbums()
    }

    override suspend fun deleteAlbums() {
        albumDAO.deleteAllAlbum()
    }

    override suspend fun insertAlbum(album: AlbumRoom) {
        albumDAO.insert(album = album)
    }
}