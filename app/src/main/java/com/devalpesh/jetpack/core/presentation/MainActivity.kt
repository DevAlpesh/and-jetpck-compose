package com.devalpesh.jetpack.core.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import coil.request.ImageRequest
import com.devalpesh.jetpack.core.presentation.components.StandardScaffold
import com.devalpesh.jetpack.core.presentation.ui.theme.AndjetpackTheme
import com.devalpesh.jetpack.core.presentation.util.Navigation
import com.devalpesh.jetpack.core.presentation.util.Screen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndjetpackTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    val navBackStackEntry = navController.currentBackStackEntryAsState()
                    val scaffoldState = rememberScaffoldState()
                    StandardScaffold(
                        navController = navController,
                        state = scaffoldState,
                        modifier = Modifier.fillMaxSize(),
                        showBottomBar = shouldShowBottomBar(navBackStackEntry.value),
                        onFabClick = {
                            navController.navigate(Screen.CreatePostScreen.route)
                        }
                    ) {
                        Navigation(navController, scaffoldState)
                    }
                }
            }
        }
    }
}

private fun shouldShowBottomBar(backStackEntry: NavBackStackEntry?) : Boolean{
    val doesRouteMatch = backStackEntry?.destination?.route in listOf(
        Screen.MainFeedScreen.route,
        Screen.ChatScreen.route,
        Screen.ActivityScreen.route,
    )
    val isOwnProfile = backStackEntry?.destination?.route == "${Screen.ProfileScreen.route}?userId={userId}" &&
            backStackEntry.arguments?.getString("userId")==null

    return doesRouteMatch || isOwnProfile
}