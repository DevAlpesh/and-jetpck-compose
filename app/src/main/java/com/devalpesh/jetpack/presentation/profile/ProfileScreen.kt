package com.devalpesh.jetpack.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.domain.models.Post
import com.devalpesh.jetpack.domain.models.User
import com.devalpesh.jetpack.presentation.components.Post
import com.devalpesh.jetpack.presentation.profile.components.BannerSection
import com.devalpesh.jetpack.presentation.profile.components.ProfileHeaderSection
import com.devalpesh.jetpack.presentation.ui.theme.ProfilePictureSizeLarge
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.util.Screen

@Composable
fun ProfileScreen(navController: NavController) {

    val toolbarOffsetY by remember {
        mutableStateOf(0f)
    }

    val toolbarHeightCollapsed = 56.dp
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    val toolbarHeightExpanded = remember {
        bannerHeight + ProfilePictureSizeLarge
    }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = toolbarOffsetY + delta
                
                return super.onPreScroll(available, source)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                Spacer(modifier = Modifier.height(toolbarHeightExpanded - ProfilePictureSizeLarge / 2f))
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
                )
            }
        }
        Column(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            BannerSection(
                modifier = Modifier.height(bannerHeight)
            )
            Image(
                painter = painterResource(id = R.drawable.philipp),
                contentDescription = stringResource(id = R.string.txt_profile_image),
                modifier = Modifier
                    .align(CenterHorizontally)
                    .graphicsLayer {
                        translationY = -ProfilePictureSizeLarge.toPx() / 2f
                    }
                    .size(ProfilePictureSizeLarge)
                    .clip(CircleShape)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onSurface,
                        shape = CircleShape
                    )
            )
        }
    }
}