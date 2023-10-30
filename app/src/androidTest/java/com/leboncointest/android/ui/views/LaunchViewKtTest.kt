package com.leboncointest.android.ui.views


import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class LaunchViewKtTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun activityNameDisplayedOnAListCard() {
        composeTestRule.setContent {
            LaunchView()
        }

        composeTestRule.onNodeWithText("sell, buy, near you")
            .assertIsDisplayed()
    }
}