package com.leboncointest.android.presentation.di

import android.app.Application
import androidx.room.Room
import com.leboncointest.android.data.db.LeBonCoinDatabase
import com.leboncointest.android.data.db.dao.AlbumDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {


    /**
     * Here we inject our room data database
     */
    @Singleton
    @Provides
    fun provideLeBonCoinDatabase(app: Application): LeBonCoinDatabase {
        return Room.databaseBuilder(app,LeBonCoinDatabase::class.java, name = "album_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Here we inject our AlbumDAO
     */
    @Singleton
    @Provides
    fun provideAlbumDao(leBonCoinDatabase: LeBonCoinDatabase): AlbumDAO {
        return leBonCoinDatabase.getAlbumsDao()
    }
}