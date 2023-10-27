package com.leboncointest.android.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AllInclusive
import androidx.compose.material.icons.outlined.EuroSymbol
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ManageAccounts
import androidx.compose.material.icons.outlined.ShoppingCart
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
import androidx.navigation.NavHostController
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModel
import com.leboncointest.android.R
import com.leboncointest.android.ui.views.bottomNavigationItems.AccountItem
import com.leboncointest.android.ui.views.bottomNavigationItems.FavoriteItem
import com.leboncointest.android.ui.views.bottomNavigationItems.MessageItem
import com.leboncointest.android.ui.views.bottomNavigationItems.PublishItem
import com.leboncointest.android.ui.views.bottomNavigationItems.searchView.SearchItem
import com.leboncointest.android.ui.views.model.BottomNavigationItem
import com.leboncointest.android.ui.views.model.Route

@Composable
fun HomeApp(
    navController: NavHostController,
    albumViewModel: AlbumViewModel
) {

    //we get the mode of our os theme
    val isDark = isSystemInDarkTheme()

    //In this list we initialize our bottom navigation items
    val bottomNavigationItems = listOf(
        BottomNavigationItem(
            Icons.Outlined.Home,
            stringResource(R.string.search_bottom_view),
            Route.SEARCHVIEW as String
        ),
        BottomNavigationItem(
            Icons.Outlined.AllInclusive,
            stringResource(R.string.favorite_bottom_view),
            Route.FAVORITEVIEW as String
        ),
        BottomNavigationItem(
            Icons.Outlined.ShoppingCart,
            stringResource(R.string.publish_bottom_view),
            Route.PUBLISHVIEW as String
        ),
        BottomNavigationItem(
            Icons.Outlined.EuroSymbol,
            stringResource(R.string.message_bottom_view),
            Route.MESSAGEVIEW as String
        ),
        BottomNavigationItem(
            Icons.Outlined.ManageAccounts,
            stringResource(R.string.account_bottom_view),
            Route.ACCOUNTVIEW as String
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
                            Image(
                                modifier = Modifier.padding(start = 5.dp),
                                painter = painterResource(id = R.mipmap.ic_launcher_round),
                                contentDescription = "The Application Launcher"
                            )
                        },
                        title = {

                        },
                        actions = {
                            // RowScope here, so these icons will be placed horizontally
                            IconButton(onClick = { /* doSomething() */ }) {
                                Icon(
                                    Icons.Outlined.FavoriteBorder,
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
                            SearchItem(navController = navController)
                        }

                        1 -> {
                            FavoriteItem(navController = navController)
                        }

                        2 -> {
                            PublishItem(navController = navController)
                        }

                        3 -> {
                            MessageItem(navController = navController)
                        }

                        else -> {
                            AccountItem(navController = navController)
                        }
                    }
                }


            }

        },
        bottomBar = {
            NavigationBar {
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