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
import com.devalpesh.jetpack.feature_activity.presentation.ActivityScreen
import com.devalpesh.jetpack.feature_auth.presentation.login.LoginScreen
import com.devalpesh.jetpack.feature_auth.presentation.register.RegisterScreen
import com.devalpesh.jetpack.feature_auth.presentation.splash.SplashScreen
import com.devalpesh.jetpack.feature_chat.presentation.chat.ChatScreen
import com.devalpesh.jetpack.feature_post.presentation.create_post.CreatePostScreen
import com.devalpesh.jetpack.feature_post.presentation.main_feed.MainFeedScreen
import com.devalpesh.jetpack.feature_post.presentation.person_list.PersonListScreen
import com.devalpesh.jetpack.feature_post.presentation.post_detail.PostDetailScreen
import com.devalpesh.jetpack.feature_profile.presentation.edit_profile.EditProfileScreen
import com.devalpesh.jetpack.feature_profile.presentation.profile.ProfileScreen
import com.devalpesh.jetpack.feature_profile.presentation.search.SearchScreen

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
                scaffoldState = scaffoldState,
                onLogin = {
                    navController.navigate(
                        route = Screen.MainFeedScreen.route,
                    ) {
                        popUpTo(Screen.LoginScreen.route) {
                            inclusive = true
                        }
                    }
                }
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
                navigateUp = navController::navigateUp,
                onNavigate = navController::navigate
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
                userId = it.arguments?.getString("userId"),
                onNavigate = navController::navigate,
                scaffoldState = scaffoldState,
                onLogout = {
                    navController.navigate(
                        route = Screen.LoginScreen.route,
                    ) {
                        popUpTo(0) {
                            inclusive = true
                        }
                    }
                }
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
        composable(
            route = Screen.PersonListScreen.route + "/{parentId}",
            arguments = listOf(
                navArgument("parentId") {
                    type = NavType.StringType
                }
            )
        ) {
            PersonListScreen(
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
                navigateUp = navController::navigateUp
            )
        }
        composable(
            route = Screen.PostDetailScreen.route + "/{postId}?shouldShowKeyboard={shouldShowKeyboard}",
            arguments = listOf(
                navArgument(
                    name = "postId"
                ) {
                    type = NavType.StringType
                },
                navArgument(
                    name = "shouldShowKeyboard"
                ) {
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            val shouldShowKeyboard = it.arguments?.getBoolean("shouldShowKeyboard") ?: false
            PostDetailScreen(
                navigateUp = navController::navigateUp,
                scaffoldState = scaffoldState,
                onNavigate = navController::navigate,
                shouldShowKeyboard = shouldShowKeyboard
            )
        }
    }
}