package com.leboncointest.android

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.model.dataRemote.response.Album

val albums = listOf(
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
)

val albumsRoom = listOf(
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



fun getAlbumsList(): SnapshotStateList<Album> {
    val albumsMutableStateOf : SnapshotStateList<Album> = mutableStateListOf()
    albums.forEach {album: Album ->
        albumsMutableStateOf.add(album)
    }
    return albumsMutableStateOf
}