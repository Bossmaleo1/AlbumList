package com.leboncointest.android.presentation.viewModel.album

import androidx.lifecycle.ViewModel
import com.leboncointest.android.domain.usecase.DeleteLocalAlbumUseCase
import com.leboncointest.android.domain.usecase.GetRemoteAlbumUseCase
import com.leboncointest.android.domain.usecase.SaveAlbumUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(
    private val getRemoteAlbumUseCase: GetRemoteAlbumUseCase,
    private val saveAlbumUseCase: SaveAlbumUseCase,
    private val deleteLocalAlbumUseCase: DeleteLocalAlbumUseCase
): ViewModel()  {

}