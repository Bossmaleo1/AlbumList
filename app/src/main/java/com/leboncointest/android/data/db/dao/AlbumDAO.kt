package com.leboncointest.android.data.db.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import kotlinx.coroutines.flow.Flow

interface AlbumDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(album: AlbumRoom)

    @Query("SELECT * FROM album_data_table")
    fun getAllAlbums(): Flow<List<AlbumRoom>>

    @Query("DELETE  FROM album_data_table")
    suspend fun deleteAllAlbum()

    @Delete
    suspend fun deleteAlbum(album: AlbumRoom)

    @Update
    suspend fun updateAlbum(album: AlbumRoom)

}