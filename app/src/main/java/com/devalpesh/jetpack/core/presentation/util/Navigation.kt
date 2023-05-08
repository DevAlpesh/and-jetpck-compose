package com.devalpesh.jetpack.core.presentation.util

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.feature_activity.presentation.activity.ActivityScreen
import com.devalpesh.jetpack.feature_auth.presentation.login.LoginScreen
import com.devalpesh.jetpack.feature_auth.presentation.register.RegisterScreen
import com.devalpesh.jetpack.feature_auth.presentation.splash.SplashScreen
import com.devalpesh.jetpack.feature_chat.presentation.chat.ChatScreen
import com.devalpesh.jetpack.feature_post.presentation.create_post.CreatePostScreen
import com.devalpesh.jetpack.feature_post.presentation.main_feed.MainFeedScreen
import com.devalpesh.jetpack.feature_post.presentation.postdetail.PostDetailScreen
import com.devalpesh.jetpack.feature_profile.presentation.edit_profile.EditProfileScreen
import com.devalpesh.jetpack.feature_profile.presentation.profile.ProfileScreen
import com.devalpesh.jetpack.feture_search.presentation.search.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route,
        modifier = Modifier.fillMaxSize()
    ) {
        composable(Screen.SplashScreen.route) {
            SplashScreen(
                onPopBackStack = navController::popBackStack,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.LoginScreen.route) {
            LoginScreen(
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.RegisterScreen.route) {
            RegisterScreen(
                onPopBackStack = navController::popBackStack,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.MainFeedScreen.route) {
            MainFeedScreen(
                navigateUp = navController::navigateUp,
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.ChatScreen.route) {
            ChatScreen()
        }
        composable(Screen.ActivityScreen.route) {
            ActivityScreen(
                navigateUp = navController::navigateUp
            )
        }
        composable(
            route = Screen.ProfileScreen.route + "?userId={userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            ProfileScreen(
                userId = it.arguments?.getString("userId") ?: "",
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState
            )
        }
        composable(
            route = Screen.EditProfileScreen.route + "/{userId}",
            arguments = listOf(
                navArgument(name = "userId") {
                    type = NavType.StringType
                }
            )
        ) {
            EditProfileScreen(
                scaffoldState = scaffoldState,
                navigateUp = navController::navigateUp
            )
        }
        composable(Screen.CreatePostScreen.route) {
            CreatePostScreen(
                navigateUp = navController::navigateUp,
                scaffoldState = scaffoldState
            )
        }
        composable(Screen.SearchScreen.route) {
            SearchScreen(
                navigateUp = navController::navigateUp,
                onNavigate = navController::navigate
            )
        }
        composable(Screen.PostDetailScreen.route) {
            PostDetailScreen(
                navigateUp = navController::navigateUp,
                post = Post(
                    username = "Philip Lackner",
                    imageUrl = "",
                    description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry" +
                            ". Lorem Ipsum has been the industry's standard dummy text ever since the 1500s," +
                            " when an unknown printer took a galley of type and scrambled it to make a type " +
                            "specimen book. It has survived not only five centuries, but also the leap into " +
                            "electronic typesetting, remaining essentially unchanged. It was popularised in" +
                            " the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, " +
                            "and more rec ently with desktop publishing software like Aldus PageMaker including " +
                            "versions of Lorem Ipsum.",
                    profilePictureUrl = "",
                    likeCount = 17,
                    commentCount = 20
                )
            )
        }
    }
}