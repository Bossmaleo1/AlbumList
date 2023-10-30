package com.leboncointest.android.ui.views.bottomNavigationItems.searchView

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.leboncointest.android.data.model.dataRemote.response.Album

@Composable
fun AlbumList(isDark: Boolean, albumList: List<Album>) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(
            top = 20.dp,
            bottom = 100.dp
        ),
        modifier = Modifier.run {
            //we make white if we have the light mode
            this.background(getSurfaceBackgroundColor(isDark))
        }
    ) {
        items(albumList) { album ->
            AlbumItem(
                album = album
            )
        }
    }
}