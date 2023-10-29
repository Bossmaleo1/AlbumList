package com.leboncointest.android.presentation.viewModel.album

import com.leboncointest.android.domain.repository.FakeAlbumRepository
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import com.leboncointest.android.getAlbumsList
import com.leboncointest.android.ui.UIEvent.ScreenState.AlbumListScreenState
import com.leboncointest.android.util.CoroutineRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
class AlbumViewModelTest {

    @get:Rule
    val coroutineRule = CoroutineRule()
    private lateinit var fakeAlbumRepository: FakeAlbumRepository
    private lateinit var deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase
    private lateinit var getLocalAlbumUseCase: GetLocalAlbumUseCase
    private lateinit var saveAlbumUseCase: SaveAlbumUseCase
    private lateinit var getRemoteAlbumUseCase : GetRemoteAlbumUseCase
    private lateinit var albumViewModel: AlbumViewModel


    fun initializeViewModel() {
        fakeAlbumRepository = FakeAlbumRepository()
        getRemoteAlbumUseCase = GetRemoteAlbumUseCase(
            albumRepository = fakeAlbumRepository
        )
        saveAlbumUseCase = SaveAlbumUseCase(
            albumRepository = fakeAlbumRepository
        )
        deleteLocalAlbumUseCase = DeleteLocalAlbumUseCase(
            albumRepository = fakeAlbumRepository
        )
        getLocalAlbumUseCase = GetLocalAlbumUseCase(
            albumRepository = fakeAlbumRepository
        )

        albumViewModel = AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        )

    }


    @Test
    fun `creating a viewmodel exposes loading ui state`() {
        this.initializeViewModel()

        //assert
        assert(albumViewModel.screenStateAlbums.value.isLoad is Boolean)
    }

    @Test
    fun `update the ui state is the getRemote is a success`() {
        this.initializeViewModel()

        val expectedUiState = AlbumListScreenState(
            isLoad = false,
            isNetworkConnected = true,
            isNetworkError = false,
            isRequested = false,
            albumList = getAlbumsList()
        )

        // Act
        coroutineRule.testDispatcher.scheduler.runCurrent()
        // Assert
        val actualState = albumViewModel.screenStateAlbums.value
        assertEquals(actualState, expectedUiState)
    }
}