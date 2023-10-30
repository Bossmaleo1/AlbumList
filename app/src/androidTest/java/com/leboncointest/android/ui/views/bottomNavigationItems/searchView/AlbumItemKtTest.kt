package com.leboncointest.android.ui.views.bottomNavigationItems.searchView

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.leboncointest.android.albums
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class AlbumItemKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun albumNameDisplayedOnCard() {

        composeTestRule.setContent {
            AlbumItem(
                album = albums[0]
            )
        }

        composeTestRule.onNodeWithText(albums[0].title)
            .assertIsDisplayed()
    }

}