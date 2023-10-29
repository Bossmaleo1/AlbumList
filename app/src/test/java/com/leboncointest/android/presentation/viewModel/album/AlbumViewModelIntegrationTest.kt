package com.leboncointest.android.presentation.viewModel.album

import com.leboncointest.android.albums
import com.leboncointest.android.albumsRoom
import com.leboncointest.android.data.apiService.AlbumAPIService
import com.leboncointest.android.data.db.dao.AlbumDAO
import com.leboncointest.android.data.repository.dataSourceImpl.AlbumRepositoryImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumLocalDataSourceImpl
import com.leboncointest.android.data.repository.dataSourceImpl.album.AlbumRemoteDataSource
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.util.CoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.times

import kotlinx.coroutines.test.*
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class AlbumViewModelIntegrationTest {

    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = StandardTestDispatcher(testScheduler)
    private val testScope = TestScope(testDispatcher)

    @get:Rule
    val coroutineRule = CoroutineRule(testDispatcher)

    private val mockApiClient: AlbumAPIService = mock()
    private val mockAlbumDAO: AlbumDAO = mock()

    private val localDataSource  = AlbumLocalDataSourceImpl(mockAlbumDAO)
    private val  remoteData = AlbumRemoteDataSource(mockApiClient)

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
}