package com.leboncointest.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.model.dataLocal.AlbumRoom

@Database(
    entities = [
        AlbumRoom::class
    ],
    version = 1,
    exportSchema = false
)
abstract class LeBonCoinDatabase : RoomDatabase()  {
    abstract fun getAlbumsDao(): AlbumDAO
}