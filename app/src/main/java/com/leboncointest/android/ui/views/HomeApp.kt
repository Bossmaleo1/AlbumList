package com.leboncointest.android.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddBox
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModel
import com.leboncointest.android.R
import com.leboncointest.android.ui.views.bottomNavigationItems.AccountItem
import com.leboncointest.android.ui.views.bottomNavigationItems.FavoriteItem
import com.leboncointest.android.ui.views.bottomNavigationItems.MessageItem
import com.leboncointest.android.ui.views.bottomNavigationItems.PublishItem
import com.leboncointest.android.ui.views.bottomNavigationItems.searchView.SearchAlbum
import com.leboncointest.android.ui.views.model.BottomNavigationItem
import com.leboncointest.android.ui.views.model.Route

@Composable
fun HomeApp() {

    //we get the mode of our os theme
    val isDark = isSystemInDarkTheme()

    //In this list we initialize our bottom navigation items
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            Icons.Outlined.Search,
            stringResource(R.string.search_bottom_view),
            Route.searchView
        ),
        BottomNavigationItem(
            Icons.Outlined.FavoriteBorder,
            stringResource(R.string.favorite_bottom_view),
            Route.favoriteView
        ),
        BottomNavigationItem(
            Icons.Outlined.AddBox,
            stringResource(R.string.publish_bottom_view),
            Route.publishView
        ),
        BottomNavigationItem(
            Icons.Outlined.ChatBubbleOutline,
            stringResource(R.string.message_bottom_view),
            Route.messageView
        ),
        BottomNavigationItem(
            Icons.Outlined.AccountCircle,
            stringResource(R.string.account_bottom_view),
            Route.accountView
        ),
    )

    //this variable save the state of the bottom navigation current item
    var selectedItem = remember { mutableIntStateOf(0) }
    val scaffoldState = rememberScaffoldState()

    Scaffold(
        topBar = {
            Scaffold(
                scaffoldState = scaffoldState,
                backgroundColor = if(!isDark) Color.White else MaterialTheme.colorScheme.background,
                topBar = {
                    //we build our home top bar
                    TopAppBar(
                        backgroundColor = if(!isDark) Color.White else MaterialTheme.colorScheme.surface,
                        navigationIcon = {

                        },
                        title = {
                            Image(
                                modifier = Modifier.padding(start = 5.dp),
                                painter = painterResource(id = R.drawable.icon_transparent),
                                contentDescription = "The Application Launcher"
                            )
                        },
                        actions = {
                            // RowScope here, so these icons will be placed horizontally
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.Notifications,
                                    contentDescription = "Localized description"
                                )
                            }
                        },
                        //we make elevation to zero because we want the plate view
                        elevation = 0.dp
                    )

                }) { innerPadding ->

                Column(
                    modifier = Modifier.padding(innerPadding)
                ) {

                    // here we initialize our content home app views
                    when (selectedItem.intValue) {
                        0 -> {
                            SearchAlbum()
                        }

                        1 -> {
                            FavoriteItem()
                        }

                        2 -> {
                            PublishItem()
                        }

                        3 -> {
                            MessageItem()
                        }

                        else -> {
                            AccountItem()
                        }
                    }
                }


            }

        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.99f)
            ) {
                bottomNavigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                            Icon(
                                item.id,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(
                                remember { item.title })
                        },
                        selected = selectedItem.intValue == index,
                        onClick = {
                            selectedItem.intValue = index
                        }
                    )
                }
            }
        }, floatingActionButton = {},
        snackbarHost = {

        },
        content = { })


}