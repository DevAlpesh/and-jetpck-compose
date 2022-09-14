package com.devalpesh.jetpack.presentation.profile

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.domain.models.Post
import com.devalpesh.jetpack.domain.models.User
import com.devalpesh.jetpack.presentation.components.Post
import com.devalpesh.jetpack.presentation.components.StandardToolbar
import com.devalpesh.jetpack.presentation.profile.components.BannerSection
import com.devalpesh.jetpack.presentation.profile.components.ProfileHeaderSection
import com.devalpesh.jetpack.presentation.ui.theme.ProfilePictureSizeLarge
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.util.Screen

@Composable
fun ProfileScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.txt_your_profile),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = false,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                BannerSection(
                    modifier = Modifier
                        .aspectRatio(2.5f)
                )
            }
            item {
                ProfileHeaderSection(
                    user = User(
                        profilePictureUrl = "",
                        username = "Alpesh Raval",
                        description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
                                "sed do eiusmod tempor incididunt ut labore et dolore magna " +
                                "aliqua. Maecenas sed enim ut sem viverra aliquet eget sit " +
                                "amet. Platea dictumst quisque sagittis purus sit amet.",
                        followerCount = 20,
                        followingCount = 30,
                        postCount = 8
                    )
                )
            }
            items(20) {
                Spacer(
                    modifier = Modifier
                        .height(SpaceMedium)
                        .offset(y = -ProfilePictureSizeLarge / 2f),
                )
                Post(
                    post = Post(
                        username = "Philip Lackner",
                        imageUrl = "",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                        profilePictureUrl = "",
                        likeCount = 17,
                        commentCount = 20
                    ),
                    onPostClick = {
                        navController.navigate(Screen.PostDetailScreen.route)
                    },
                    showProfileImage = false,
                    modifier = Modifier
                        .offset(y = -ProfilePictureSizeLarge / 2f)
                )
            }
        }
    }
}