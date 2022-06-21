package com.devalpesh.jetpack.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devalpesh.jetpack.presentation.login.LoginScreen
import com.devalpesh.jetpack.presentation.splash.SplashScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(Screen.RegisterScreen.route) {

        }
    }
}