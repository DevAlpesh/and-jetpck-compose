package com.devalpesh.jetpack.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devalpesh.jetpack.presentation.activity.ActivityScreen
import com.devalpesh.jetpack.presentation.chat.ChatScreen
import com.devalpesh.jetpack.presentation.createpost.CreatePostScreen
import com.devalpesh.jetpack.presentation.login.LoginScreen
import com.devalpesh.jetpack.presentation.mainfeed.MainFeedScreen
import com.devalpesh.jetpack.presentation.profile.ProfileScreen
import com.devalpesh.jetpack.presentation.register.RegisterScreen
import com.devalpesh.jetpack.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController

) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(navController = navController)
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen(navController = navController)
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(navController = navController)
        }
        composable(Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }
    }
}