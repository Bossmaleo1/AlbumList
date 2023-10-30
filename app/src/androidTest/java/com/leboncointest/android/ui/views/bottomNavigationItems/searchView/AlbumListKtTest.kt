package com.leboncointest.android.ui.views.bottomNavigationItems.searchView

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.leboncointest.android.albums
import com.leboncointest.android.data.model.dataRemote.response.Album
import org.junit.Rule
import org.junit.Test

class AlbumListKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun activityListDisplaysAllActivities() {
        composeTestRule.setContent {
            AlbumList(true, albums)
        }

        albums.forEach { album: Album ->
            composeTestRule.onNodeWithText(album.title)
                .assertIsDisplayed()
        }
    }
}