package com.leboncointest.android.data.repository.dataSourceImpl.album

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.db.LeBonCoinDatabase
import com.leboncointest.android.data.db.dao.AlbumDAO
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AlbumLocalDataSourceImplTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: LeBonCoinDatabase
    private lateinit var albumDAO: AlbumDAO

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, LeBonCoinDatabase::class.java).build()
        albumDAO = database.getAlbumsDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    fun canSaveAlbumToTheDbAndReadIt() = runTest {
        // Arrange
        val albumLocalDataSource = AlbumLocalDataSourceImpl(albumDAO)

        // Act
        albumLocalDataSource.insertAlbum(album = albumsRoom[0])
        val expectedAlbum = albumsRoom[0]
        //Test if we have inserted our element
        albumDAO.getAllAlbums().test {
            val albumsLocal = awaitItem()
            Truth.assertThat(albumsLocal.size).isEqualTo(1)
            Truth.assertThat(albumsRoom[0].title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
            Truth.assertThat(albumsRoom[0].albumId).isEqualTo(1)
            Truth.assertThat(albumsRoom[0].id).isEqualTo(1)
            Truth.assertThat(albumsRoom[0].url).isEqualTo("https://via.placeholder.com/600/92c952")
            Truth.assertThat(albumsRoom[0].thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
        }
    }

    @Test
    fun canDeleteAlbumFromTheDb() = runTest {
        // Arrange
        val albumLocalDataSource = AlbumLocalDataSourceImpl(albumDAO)
        albumLocalDataSource.insertAlbum(album = albumsRoom[0])


        // Act
        albumLocalDataSource.deleteAlbums()

        //Test if we have inserted our element
        albumDAO.getAllAlbums().test {
            val albumsLocal = awaitItem()
            Truth.assertThat(albumsLocal.size).isEqualTo(0)
        }
    }

    @Test
    fun canGetAlbumFromTheDb() = runTest {
        // Arrange
        val albumLocalDataSource = AlbumLocalDataSourceImpl(albumDAO)
        //we insert our album
        albumsRoom.forEach {albumRoom ->
            albumLocalDataSource.insertAlbum(album = albumRoom)
        }

        // Act
        val albumsLocal = AlbumLocalDataSourceImpl(albumDAO).getLocalAlbums()
        //Assert
        albumsLocal.test {
            val albumsLocal = awaitItem()
            Truth.assertThat(albumsLocal.size).isEqualTo(4)
        }
    }

}