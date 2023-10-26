package com.leboncointest.android.data.model.dataLocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "album_data_table"
)
data class AlbumRoom(
    @ColumnInfo(name = "album_albumId")
    @PrimaryKey(autoGenerate = false)
    var albumId: Long?,
    @ColumnInfo(name = "album_id")
    var id: Long?,
    @ColumnInfo("album_title")
    var title: String,
    @ColumnInfo("album_url")
    var url: String,
    @ColumnInfo("album_thumbnailUrl")
    var thumbnailUrl: String
)
