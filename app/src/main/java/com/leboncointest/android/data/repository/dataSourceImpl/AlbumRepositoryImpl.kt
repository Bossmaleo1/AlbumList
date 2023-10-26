package com.leboncointest.android.data.repository.dataSourceImpl

import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.data.repository.dataSource.album.AlbumLocalDataSource
import com.leboncointest.android.data.repository.dataSource.album.AlbumRemoteDataSource
import com.leboncointest.android.domain.repository.AlbumRepository
import com.leboncointest.android.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class AlbumRepositoryImpl(
    private val albumLocalDataSource: AlbumLocalDataSource,
    private val albumRemoteDataSource: AlbumRemoteDataSource
) : AlbumRepository {

    /**
     * This method convert response to ressource albums
     */
    private fun responseToResourceAlbums(response: Response<List<Album>>): Resource<List<Album>> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }

    override suspend fun getAlbums(): Resource<List<Album>> {
        return responseToResourceAlbums(
            albumRemoteDataSource.getAlbums()
        )
    }

    override fun getLocalAlbums(): Flow<List<AlbumRoom>> {
       return albumLocalDataSource.getLocalAlbums()
    }

    override suspend fun deleteAlbums() {
        albumLocalDataSource.deleteAlbums()
    }

    override suspend fun insertAlbum(album: AlbumRoom) {
        albumLocalDataSource.insertAlbum(album)
    }


}