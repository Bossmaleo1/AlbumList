package com.leboncointest.android.ui.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leboncointest.android.R

@Composable
fun LaunchView() {
    //we get the mode of our os theme
    val isDark = isSystemInDarkTheme()

    Column(
        modifier = Modifier.fillMaxSize().run {
            //we make white if we have the light mode
            if (!isDark) {
                this.background(Color.White)
            } else {
                this
            }

        },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Image(
                painter = painterResource(id = R.mipmap.ic_launcher_round) ,
                contentDescription = "The Application Launcher"
            )
        }

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(
                text = stringResource(R.string.launch_title),
                style = MaterialTheme.typography.titleSmall,
                color = if(isDark) Color.White else MaterialTheme.colorScheme.primary
            )
        }

    }
}