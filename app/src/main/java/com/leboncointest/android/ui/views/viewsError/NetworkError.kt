package com.leboncointest.android.ui.views.viewsError

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material.icons.outlined.SignalCellularConnectedNoInternet0Bar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.leboncointest.android.R

@Composable
fun NetworkError(
    title: String,
    iconValue: Int
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(Color.Transparent)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(40.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                when (iconValue) {
                    0 -> {
                        Icons.Outlined.SignalCellularConnectedNoInternet0Bar
                    }
                    1 -> {
                        Icons.Outlined.Block
                    }
                    else -> {
                        Icons.Outlined.Block
                    }
                },
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                tint = Color.Red
            )

            Text(
                text = title,
                color = Color.Red,
                modifier = Modifier.padding(start = 4.dp, top = 10.dp)
            )

            OutlinedButton(
                modifier = Modifier
                    .width(280.dp)
                    .padding(top = 30.dp),
                border = BorderStroke(1.dp, color = MaterialTheme.colorScheme.primary),
                onClick = {

                }) {
                Icon(
                    imageVector = Icons.Outlined.Refresh,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.size(ButtonDefaults.IconSpacing))
                Text(stringResource(R.string.retry_leboncoin))
            }
        }
    }
}