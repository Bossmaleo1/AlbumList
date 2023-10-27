package com.leboncointest.android.ui.views.bottomNavigationItems.searchView

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.leboncointest.android.data.model.dataRemote.response.Album

@Composable
fun AlbumItem(
    navController: NavHostController,
    album: Album
) {

    Box(
        Modifier.fillMaxSize().padding(top = 15.dp)
    ) {

        Box(
            modifier = Modifier.align(Alignment.TopEnd)
        ) {
            IconButton(onClick = { /* doSomething() */ }, modifier = Modifier
                .padding(bottom = 10.dp)
                .wrapContentWidth()
                .wrapContentHeight()) {
                Icon(
                    Icons.Outlined.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = 22.dp)
                        .wrapContentWidth()
                        .wrapContentHeight()
                )
            }
        }

        Box( modifier = Modifier.align(Alignment.BottomCenter)) {
            Column {

                Row {
                    //we test if we have a image to catch exception
                    //if (product.imagesUrls.isNotEmpty()) {
                        Image(
                            painter = getAlbumImage(""),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(top = 20.dp, bottom = 20.dp, start = 5.dp, end = 5.dp)
                                .height(100.dp)
                                .width(100.dp)
                        )
                    //}


                    Column {

                        Row {
                            Text(
                                text = "Testing 1",
                                style = MaterialTheme.typography.titleSmall,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(end = 35.dp)
                            )


                        }

                        Text(
                            text = "Samsung - Téléphone Samsung Galaxy...",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 35.dp),
                            fontSize = 10.sp

                        )

                    }
                }

                Row {
                    Divider(
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(top = 15.dp)
                            .fillMaxWidth()
                            .height(0.20.dp),
                    )
                }
            }
        }

    }
}

/**
 * This function help us to display our image
 */
@Composable
fun getAlbumImage(image: String): Painter {
    return rememberAsyncImagePainter(image)
}