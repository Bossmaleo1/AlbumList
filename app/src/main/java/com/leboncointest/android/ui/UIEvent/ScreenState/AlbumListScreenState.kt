package com.leboncointest.android.ui.UIEvent.ScreenState

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.model.dataRemote.response.Album

data class AlbumListScreenState(
    var isNetworkConnected: Boolean = true,
    var albumList : SnapshotStateList<Album> = mutableStateListOf(),
    var albumRoomList: List<AlbumRoom> = mutableListOf(),
    var isLoad: Boolean = false
)
