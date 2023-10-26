package com.leboncointest.android

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
            Resource.Success(listOf(
                Album(
                    albumId = 1,
                    id = 1,
                    title = "accusamus beatae ad facilis cum similique qui sunt",
                    url = "https://via.placeholder.com/600/92c952",
                    thumbnailUrl = "https://via.placeholder.com/150/92c952"
                ),
                Album(
                    albumId = 1,
                    id = 2,
                    title = "reprehenderit est deserunt velit ipsam",
                    url = "https://via.placeholder.com/600/24f355",
                    thumbnailUrl = "https://via.placeholder.com/150/24f355"
                ),
                Album(
                    albumId = 1,
                    id = 3,
                    title = "officia porro iure quia iusto qui ipsa ut modi",
                    url = "https://via.placeholder.com/600/24f355",
                    thumbnailUrl = "https://via.placeholder.com/150/24f355"
                ),
                Album(
                    albumId = 1,
                    id = 4,
                    title = "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
                    url = "https://via.placeholder.com/600/d32776",
                    thumbnailUrl = "https://via.placeholder.com/150/d32776"
                )
            ))
        }
    }

    override fun getLocalAlbums(): Flow<List<AlbumRoom>> {
       return flow {
           emit(
               listOf(
                   AlbumRoom(
                       albumId = 1,
                       id = 1,
                       title = "accusamus beatae ad facilis cum similique qui sunt",
                       url = "https://via.placeholder.com/600/92c952",
                       thumbnailUrl = "https://via.placeholder.com/150/92c952"
                   ),
                   AlbumRoom(
                       albumId = 1,
                       id = 2,
                       title = "reprehenderit est deserunt velit ipsam",
                       url = "https://via.placeholder.com/600/24f355",
                       thumbnailUrl = "https://via.placeholder.com/150/24f355"
                   ),
                   AlbumRoom(
                       albumId = 1,
                       id = 3,
                       title = "officia porro iure quia iusto qui ipsa ut modi",
                       url = "https://via.placeholder.com/600/24f355",
                       thumbnailUrl = "https://via.placeholder.com/150/24f355"
                   ),
                   AlbumRoom(
                       albumId = 1,
                       id = 4,
                       title = "culpa odio esse rerum omnis laboriosam voluptate repudiandae",
                       url = "https://via.placeholder.com/600/d32776",
                       thumbnailUrl = "https://via.placeholder.com/150/d32776"
                   )
               )
           )
       }
    }

    override suspend fun deleteAlbums() {
        TODO("Not yet implemented")
    }

    override suspend fun insertAlbum(album: AlbumRoom) {
        TODO("Not yet implemented")
    }


}