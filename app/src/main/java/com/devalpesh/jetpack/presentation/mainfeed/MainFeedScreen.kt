package com.devalpesh.jetpack.presentation.mainfeed

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.devalpesh.jetpack.presentation.components.Post
import com.devalpesh.jetpack.presentation.components.StandardScaffold

@Composable
fun MainFeedScreen(
    navController: NavController
) {
    Post(
        post = com.devalpesh.jetpack.domain.models.Post(
            username = "Philip Lackner",
            imageUrl = "",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            profilePictureUrl = "",
            likeCount = 17,
            commentCount = 20
        )
    )
}