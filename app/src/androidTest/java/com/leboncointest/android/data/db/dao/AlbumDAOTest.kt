package com.leboncointest.android.data.db.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.cash.turbine.test
import com.google.common.truth.Truth
import com.leboncointest.android.data.db.LeBonCoinDatabase
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumDAOTest {

    private lateinit var dao: AlbumDAO
    private lateinit var database: LeBonCoinDatabase

    // we initialize our room data product list
    val albums = listOf(
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

    //we initialize our field
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            LeBonCoinDatabase::class.java
        ).build()
        dao = database.getAlbumsDao()
    }

    /**
     * This method insert our product list
     * and should be used in our tests
     */
    fun insertAlbumList() = runTest {
        albums.forEach {albumRoom ->
            dao.insert(album = albumRoom)
        }
    }

    //here we close our database
    @After
    fun cleanup() {
        database.close()
    }

    @Test
    fun get_all_database_albums_Test() = runTest {
        //before we insert our album data
        insertAlbumList()

        //now we  our get all albums method to test it
        dao.getAllAlbums().test {
            //we get our flow item
            val albums = awaitItem()
            Truth.assertThat(albums.size).isEqualTo(4)
            //we test our second album list title item
            Truth.assertThat(albums[0].title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
            Truth.assertThat(albums[0].albumId).isEqualTo(1)
            Truth.assertThat(albums[0].id).isEqualTo(1)
            Truth.assertThat(albums[0].url).isEqualTo("https://via.placeholder.com/600/92c952")
            Truth.assertThat(albums[0].thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
            cancel()
        }
    }

    @Test
    fun delete_all_albums_Test() = runTest {
        //we insert our albums before to delete it
        insertAlbumList()

        //we test we have really two item
        dao.getAllAlbums().test {
            //we get our flow item
            val albums = awaitItem()
            Truth.assertThat(albums.size).isEqualTo(2)
            cancel()
        }
        //we delete our all album
        dao.deleteAllAlbum()
        //we test if we have 0 element in our albums list
        dao.getAllAlbums().test {
            //we get our flow item
            val albums = awaitItem()
            Truth.assertThat(albums.size).isEqualTo(0)
            cancel()
        }
    }

    @Test
    fun update_album_Test() = runTest {
        //we insert our albums before to update it
        insertAlbumList()


        /**
         *  now we  our get all products method to test it
         *  we test our second product list headline item before the update
         */
        dao.getAllAlbums().test {
            //we get our flow item
            val albums = awaitItem()
            //we test our second product list headline item
            //we test our second album list title item
            Truth.assertThat(albums[0].title).isEqualTo("accusamus beatae ad facilis cum similique qui sunt")
            Truth.assertThat(albums[0].albumId).isEqualTo(1)
            Truth.assertThat(albums[0].id).isEqualTo(1)
            Truth.assertThat(albums[0].url).isEqualTo("https://via.placeholder.com/600/92c952")
            Truth.assertThat(albums[0].thumbnailUrl).isEqualTo("https://via.placeholder.com/150/92c952")
            cancel()
        }


        // now we update our second products headline
        //products[1].headline = "Samsung Galaxy S21 5G 128 Go Double SIM Violet Sidney of rakuten set this headline"
        dao.updateAlbum(AlbumRoom(
            albumId = 1,
            id = 1,
            title = "Testing LeBonCoin",
            url = "https://via.placeholder.com/600/92c952",
            thumbnailUrl = "https://via.placeholder.com/150/92c952"
        ))

        dao.getAllAlbums().test {
            //we get our flow item
            val albums = awaitItem()
            //we test our second product list headline item
            // to verify if our update was be a success
            Truth.assertThat(albums[0].title).isEqualTo("Testing LeBonCoin")
            cancel()
        }
    }

}