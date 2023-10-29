package com.leboncointest.android.presentation.viewModel.album

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.leboncointest.android.albums
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.model.dataLocal.AlbumRoom
import com.leboncointest.android.data.repository.AlbumRepositoryImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumLocalDataSourceImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumRemoteDataSourceImpl
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.ui.UIEvent.Event.AlbumEvent
import com.leboncointest.android.ui.UIEvent.UIEvent
import com.leboncointest.android.util.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
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
    fun `calling getAlbums() triggers the api client`() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )

        // Act
        albumViewModel.getRemoteAlbums()
        runCurrent()

        // Assert
        verify(mockApiClient, times(1)).getAlbums()
    }

    @Test
    fun `calling save album triggers the dao insert`() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )


        // Act
        albumViewModel.insertAlbums(albums)
        runCurrent()

        // Assert
        verify(mockAlbumDAO, times(1)).insert(albumsRoom[0])
    }

    @Test
    fun `calling delete all albums triggers the dao delete`() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )

        // Act
        albumViewModel.deleteAlbums()
        runCurrent()

        // Assert
        verify(mockAlbumDAO, times(1)).deleteAllAlbum()
    }

    @Test
    fun `we test if we return isConnected SnackBar Message`() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )

        // Act
        albumViewModel.onEvent(
            AlbumEvent.IsNetworkConnected("You are disconnected, please review your connection")
        )

        // Assert
        albumViewModel.uiEventFlow.test {
            //we get our flow item
            val showMessage =  awaitItem() as UIEvent.ShowMessage
            assertThat(showMessage.message).isEqualTo("You are disconnected, please review your connection")
        }
    }

    @Test
    fun `we test if getAlbumList retrieve the localAlbumList`() = runTest {
        // Arrange
        val albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )
        //Act
        val albumLocalLiveData = albumViewModel.getAlbumList()


        assert(albumLocalLiveData is LiveData<List<AlbumRoom>>)

    }

}