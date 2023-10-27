package com.leboncointest.android.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModel
import com.leboncointest.android.presentation.viewModel.album.AlbumViewModelFactory
import com.leboncointest.android.ui.theme.LeBonCoinTheme
import com.leboncointest.android.ui.views.HomeApp
import com.leboncointest.android.ui.views.LaunchView
import com.leboncointest.android.ui.views.model.Route
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var albumFactory: AlbumViewModelFactory
    private lateinit var albumViewModel: AlbumViewModel

    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeBonCoinTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    //we initialize our MainView
                    MainView(navController)
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(200)
                        navController.navigate(Route.homeView)
                    }
                }
            }
        }
    }

    private fun initViewModel() {
        albumViewModel = ViewModelProvider(owner = this, albumFactory)[AlbumViewModel::class.java]
    }

    /***
     * This method initialize our view navigation
     */
    @Composable
    fun MainView(navController: NavHostController) {

        val activity = (LocalContext.current as? Activity)

        //We call our init view model method
        this.initViewModel()

        NavHost(navController = navController, startDestination = Route.launchView) {
            //This is our launch view navigation initialize
            composable(route = Route.launchView) {
                LaunchView()
                BackHandler {
                    activity?.finish()
                }
            }

            //This is our home view navigation initialize
            composable(
                route = Route.homeView
            ) {
                HomeApp(
                    navController = navController,
                    albumViewModel = albumViewModel
                )
                //this instruction help the user to exit of the app
                //after he press the back button
                BackHandler {
                    activity?.finish()
                }
            }
        }
    }

}
