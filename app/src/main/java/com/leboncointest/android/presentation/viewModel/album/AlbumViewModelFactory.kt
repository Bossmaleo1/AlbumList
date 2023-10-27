package com.leboncointest.android.presentation.viewModel.album

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase

class AlbumViewModelFactory(
    private val getRemoteAlbumUseCase: GetRemoteAlbumUseCase,
    private val saveAlbumUseCase: SaveAlbumUseCase,
    private val deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase,
    private val getLocalAlbumUseCase: GetLocalAlbumUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AlbumViewModel(
            getRemoteAlbumUseCase = getRemoteAlbumUseCase,
            saveAlbumUseCase = saveAlbumUseCase,
            deleteLocalAlbumUseCase = deleteLocalAlbumUseCase,
            getLocalAlbumUseCase = getLocalAlbumUseCase
        ) as T
    }
}