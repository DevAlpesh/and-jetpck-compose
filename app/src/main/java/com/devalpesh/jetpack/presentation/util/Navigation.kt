package com.devalpesh.jetpack.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.devalpesh.jetpack.domain.models.Post
import com.devalpesh.jetpack.presentation.activity.ActivityScreen
import com.devalpesh.jetpack.presentation.chat.ChatScreen
import com.devalpesh.jetpack.presentation.createpost.CreatePostScreen
import com.devalpesh.jetpack.presentation.editprofile.EditProfileScreen
import com.devalpesh.jetpack.presentation.login.LoginScreen
import com.devalpesh.jetpack.presentation.mainfeed.MainFeedScreen
import com.devalpesh.jetpack.presentation.postdetail.PostDetailScreen
import com.devalpesh.jetpack.presentation.profile.ProfileScreen
import com.devalpesh.jetpack.presentation.register.RegisterScreen
import com.devalpesh.jetpack.presentation.splash.SplashScreen

@Composable
fun Navigation(
    navController: NavHostController

) {
    NavHost(
        navController = navController,
        startDestination = Screen.EditProfileScreen.route,
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
        composable(Screen.EditProfileScreen.route) {
            EditProfileScreen(navController = navController)
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(navController = navController)
        }
        composable(Screen.PostDetailScreen.route) {
            PostDetailScreen(
                navController = navController,
                post = Post(
                    username = "Philip Lackner",
                    imageUrl = "",
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry" +
                            ". Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type " +
                            "specimen book. It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged. It was popularised in" +
                            " the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                            "and more recently with desktop publishing software like Aldus PageMaker including " +
                            "versions of Lorem Ipsum.",
                    profilePictureUrl = "",
                    likeCount = 17,
                    commentCount = 20
                )
            )
        }
    }
}