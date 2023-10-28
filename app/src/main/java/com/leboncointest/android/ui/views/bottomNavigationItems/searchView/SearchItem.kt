package com.leboncointest.android.ui.views.bottomNavigationItems.searchView


import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.GpsFixed
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.leboncointest.android.R
import com.leboncointest.android.data.model.dataRemote.response.Album
import com.leboncointest.android.presentation.util.isNetworkAvailable
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModel
import com.leboncointest.android.ui.UIEvent.Event.AlbumEvent
import com.leboncointest.android.ui.UIEvent.UIEvent
import com.leboncointest.android.ui.views.progressbar.SpinnerCenterVerticalHorizontal
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchAlbum(
    albumViewModel: AlbumViewModel
) {

    //we get the mode of our os theme
    val isDark = isSystemInDarkTheme()
    //we get our screen state in our viewModel
    val screenState = albumViewModel.screenStateAlbums.value
    //this variable help use
    // to observe the state of our keyup product search value
    var searchElement by rememberSaveable { mutableStateOf("") }
    //we get our application context
    val context = LocalContext.current

    var isRequested = rememberSaveable {
        mutableStateOf(true)
    }

    var isNetworkFailed = rememberSaveable {
        mutableStateOf(true)
    }
    //we initialize our snackbar host State
    val snackbarHostState = remember { SnackbarHostState() }

    val scaffoldState = rememberScaffoldState()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = getBackgroundColor(isDark = isDark),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(bottom = 80.dp)
            )
        }
    ) { paddingValue ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue).run {
                    //we make white if we have the light mode
                    this.background(getSurfaceBackgrounClor(isDark))
                }
        ) {
            Spacer(modifier = Modifier.size(10.dp))

            OutlinedTextField(
                value = searchElement,
                singleLine = true,
                textStyle = TextStyle(fontSize = 12.sp),
                onValueChange = {
                    searchElement = it
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                placeholder = {
                    Text(text = stringResource(R.string.search_for_leboncoin))
                },
                leadingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Outlined.GpsFixed,
                            contentDescription = "",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp, bottom = 0.dp, start = 10.dp, end = 10.dp)
                    .height(55.dp),
                shape = RoundedCornerShape(8.dp)
            )

            //we display our spinner if we request our network
            SpinnerCenterVerticalHorizontal(screenState.isLoad)



            Row {

                /**
                 * We build the LazyRow
                 * to illustrate a UI as
                 * in the real application
                 */
                LazyColumn(
                    state = rememberLazyListState(),
                    contentPadding = PaddingValues(
                        top = 20.dp,
                        bottom = 100.dp
                    ),
                    modifier = Modifier.run {
                        //we make white if we have the light mode
                        if (!isDark) {
                            this.background(Color.White)
                        }
                        this.background(MaterialTheme.colorScheme.surface)
                    }
                ) {
                    items(screenState.albumList) { album ->
                        AlbumItem(
                            album = album
                        )
                    }


                }
            }
        }
    }

    //we observe our network state
    if (!screenState.isNetworkConnected) {
        albumViewModel.onEvent(AlbumEvent.IsNetworkConnected(context.getString(R.string.is_connect_error)))
    }

    //isrequesred help us to make our http call juste one time
    LaunchedEffect(key1 = isRequested) {
        //we call our api
        albumViewModel.onEvent(AlbumEvent.GetRemoteAlbums(app = context))
        isRequested.value = false
    }
    /**
     * we test if we are offline to load our sqlite database datas
     */
    if (!isNetworkAvailable(context)) {
        //we load our locale datas
        LaunchedEffect(key1 = isNetworkFailed) {


            //we get our product list
            albumViewModel.getAlbumList().observe(context as LifecycleOwner) {albums->

                // Before we clean our screen state product list
                screenState.albumList.removeAll(screenState.albumList)

                albums.forEach { albumRoom ->
                    albumRoom.albumId?.let {
                        albumRoom.id?.let { it1 ->
                            Album(
                                albumId = it,
                                id = it1,
                                title = albumRoom.title,
                                url = albumRoom.url,
                                thumbnailUrl = albumRoom.thumbnailUrl
                            )
                        }
                    }?.let {
                        screenState.albumList.add(
                            it
                        )
                    }
                }
            }

            //we close our block
            isNetworkFailed.value = false
        }

        LaunchedEffect(key1 = !screenState.isNetworkConnected) {
            albumViewModel.uiEventFlow.collectLatest { event ->
                when (event) {
                    is UIEvent.ShowMessage -> {
                        snackbarHostState.showSnackbar(
                            message = event.message,
                            duration = SnackbarDuration.Long
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun getBackgroundColor(isDark: Boolean): Color {
   return if (isDark) Color.White else MaterialTheme.colorScheme.background
}

@Composable
fun getSurfaceBackgrounClor(isDark: Boolean): Color{
      return if (!isDark) {
        Color.White
    } else {
        MaterialTheme.colorScheme.surface
    }
}


