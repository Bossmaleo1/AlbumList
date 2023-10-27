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
import androidx.compose.material3.Text
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
import com.leboncointest.android.ui.views.LaunchView
import com.leboncointest.android.ui.views.model.Route
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

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
                        navController.navigate(Route.HOMEVIEW as String)
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

        NavHost(navController = navController, startDestination = (Route.LAUNCHVIEW as String)) {
            //This is our launch view navigation initialize
            composable(route = Route.LAUNCHVIEW) {
                LaunchView()
                BackHandler {
                    activity?.finish()
                }
            }
        }
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}
