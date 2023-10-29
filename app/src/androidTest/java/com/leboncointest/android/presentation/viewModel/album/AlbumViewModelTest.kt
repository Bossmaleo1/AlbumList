package com.leboncointest.android.presentation.viewModel.album

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth
import com.leboncointest.android.CoroutineRule
import com.leboncointest.android.albums
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.repository.AlbumRepositoryImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumLocalDataSourceImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumRemoteDataSourceImpl
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.getAlbumsList
import com.leboncointest.android.presentation.util.isNetworkAvailable
import com.leboncointest.android.ui.UIEvent.Event.AlbumEvent
import com.leboncointest.android.ui.UIEvent.ScreenState.AlbumListScreenState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.whenever
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AlbumViewModelTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    private val mockApiClient: AlbumAPIService = mock()
    private val mockAlbumDAO: AlbumDAO = mock()

    private val localDataSource  = AlbumLocalDataSourceImpl(mockAlbumDAO)
    private val  remoteData = AlbumRemoteDataSourceImpl(mockApiClient)

    private val albumRepository = AlbumRepositoryImpl(
        albumLocalDataSource = localDataSource,
        albumRemoteDataSource = remoteData
    )

    private  val deleteLocalAlbumUseCase = DeleteLocalAlbumUseCase(albumRepository)
    private  val getLocalAlbumUseCase =  GetLocalAlbumUseCase(albumRepository)
    private  val saveAlbumUseCase = SaveAlbumUseCase(albumRepository)
    private  val getRemoteAlbumUseCase = GetRemoteAlbumUseCase(albumRepository)

    @Test
    fun canHaveGoodNetWorkStateAndGoodLoadingState() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )


        val isConnected = isNetworkAvailable(ApplicationProvider.getApplicationContext())

        //Act
        runCurrent()

        // Assert
        val screenState = albumViewModel.screenStateAlbums.value
        assert(screenState is AlbumListScreenState)
        assertEquals(screenState.isNetworkConnected, isConnected)
        assertEquals(screenState.isLoad, false)
    }



    @Test
    fun canRetrieveAlbumListIfWeHaveNetworkEnabled() = runTest {
        // Arrange
         whenever(mockApiClient.getAlbums()).doReturn(Response.success(albums))
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )

        // Act
        albumViewModel.onEvent(
            AlbumEvent.GetRemoteAlbums(
                app = ApplicationProvider.getApplicationContext()
            )
        )
        val expectedAlbums = albums

        //Act
        runCurrent()

        // Assert
        val screenState = albumViewModel.screenStateAlbums.value
        assertEquals(screenState.albumList.size, 4)
        assertEquals(screenState.albumList[0].albumId, albums[0].albumId)
        assertEquals(screenState.albumList[0].id, albums[0].id)
        assertEquals(screenState.albumList[0].url, albums[0].url)
        assertEquals(screenState.albumList[0].thumbnailUrl, albums[0].thumbnailUrl)
    }

}