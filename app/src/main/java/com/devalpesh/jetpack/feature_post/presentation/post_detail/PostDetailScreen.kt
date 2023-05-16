package com.devalpesh.jetpack.feature_post.presentation.post_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.presentation.components.ActionRow
import com.devalpesh.jetpack.core.presentation.components.StandardTextField
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.ui.theme.MediumGray
import com.devalpesh.jetpack.core.presentation.ui.theme.ProfilePictureSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.core.presentation.util.Screen
import com.devalpesh.jetpack.core.presentation.util.UiEvent
import com.devalpesh.jetpack.core.presentation.util.asString
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PostDetailScreen(
    scaffoldState: ScaffoldState,
    navigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: PostDetailsViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val commentTextFieldState = viewModel.commentTextStateFieldState.value

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.uiText.asString(context)
                    )
                }

                else -> {}
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navigateUp = navigateUp,
            title = {
                Text(
                    text = stringResource(id = R.string.txt_your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .background(MaterialTheme.colors.surface),
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colors.background)
                ) {
                    Spacer(modifier = Modifier.height(SpaceLarge))
                    Box(
                        modifier = Modifier
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = ProfilePictureSizeMedium / 2f)
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(5.dp)
                                .background(MediumGray)
                        ) {
                            state.post?.let { post ->
                                Image(
                                    rememberAsyncImagePainter(model = post.imageUrl),
                                    contentDescription = "Post Image",
                                    modifier = Modifier
                                        .aspectRatio(16f / 9f)
                                )
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(SpaceLarge)
                                ) {
                                    ActionRow(
                                        modifier = Modifier.fillMaxWidth(),
                                        onLikeClick = {
                                            viewModel.onEvent(PostDetailsEvent.LikePost)
                                        },
                                        onCommentClick = {

                                        },
                                        onShareClick = {

                                        },
                                        onUsernameClick = {

                                        },
                                        username = post.username,
                                        isLiked = post.isLiked
                                    )
                                    Spacer(modifier = Modifier.height(SpaceSmall))
                                    Text(
                                        text = post.description,
                                        style = MaterialTheme.typography.body2,
                                    )
                                    Spacer(modifier = Modifier.height(SpaceMedium))
                                    Text(
                                        text = stringResource(
                                            id = R.string.like_by_x_peoper,
                                            post.likeCount
                                        ),
                                        fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.body2,
                                        fontSize = 16.sp,
                                        modifier = Modifier.clickable {
                                            onNavigate(Screen.PersonListScreen.route + "/${post.id}")
                                        }
                                    )
                                }
                            }
                        }
                        Image(
                            rememberAsyncImagePainter(model = state.post?.profilePictureUrl),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(ProfilePictureSizeMedium)
                                .clip(CircleShape)
                                .align(Alignment.TopCenter)
                        )
                        if (state.isLoadingPost) {
                            CircularProgressIndicator(
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
            items(state.comments) { comment ->
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall
                        ),
                    comment = comment,
                    onLikeClick = {
                        viewModel.onEvent(PostDetailsEvent.LikeComment(comment.id))
                    },
                    onLikeByClick = {
                        onNavigate(Screen.PersonListScreen.route + "/${comment.id}")
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.surface)
                .padding(SpaceLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StandardTextField(
                text = commentTextFieldState.text,
                onValueChange = {
                    viewModel.onEvent(PostDetailsEvent.EnteredComment(it))
                },
                modifier = Modifier
                    .weight(1f),
                backgroundColor = MaterialTheme.colors.background,
                hint = stringResource(id = R.string.txt_enter_comment)
            )

            if (viewModel.commentState.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .size(24.dp),
                    strokeWidth = 2.dp
                )
            } else {
                IconButton(
                    onClick = {
                        viewModel.onEvent(PostDetailsEvent.Comment)
                    }) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = stringResource(id = R.string.txt_icon_send),
                        tint = if (commentTextFieldState.error == null) {
                            MaterialTheme.colors.primary
                        } else {
                            MaterialTheme.colors.background
                        }
                    )
                }
            }
        }
    }
}