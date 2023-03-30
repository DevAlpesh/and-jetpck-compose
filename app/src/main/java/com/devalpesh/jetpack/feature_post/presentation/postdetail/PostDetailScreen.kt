package com.devalpesh.jetpack.feature_post.presentation.postdetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Comment
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.presentation.components.ActionRow
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.ui.theme.*

@Composable
fun PostDetailScreen(
    navController: NavController,
    post: Post
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            title = {
                Text(
                    text = stringResource(id = R.string.txt_your_feed),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
            modifier = Modifier.fillMaxWidth(),
            showBackArrow = true,
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
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
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .offset(y = ProfilePictureSizeMedium / 2f)
                                .clip(MaterialTheme.shapes.medium)
                                .shadow(5.dp)
                                .background(MediumGray)
                        ) {
                            Image(
                                painterResource(id = R.drawable.kermit),
                                contentDescription = "Post Image",
                                modifier = Modifier.fillMaxWidth()
                            )
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(SpaceLarge)
                            ) {
                                ActionRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    onLikeClick = {

                                    },
                                    onCommentClick = {

                                    },
                                    onShareClick = {

                                    },
                                    onUsernameClick = {

                                    },
                                    username = "Philip Lackner",
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
                                )
                            }
                        }
                        Image(
                            painterResource(id = R.drawable.philipp),
                            contentDescription = "Profile Picture",
                            modifier = Modifier
                                .size(ProfilePictureSizeMedium)
                                .clip(CircleShape)
                                .align(Alignment.TopCenter)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(SpaceLarge))
            }
            items(20) {
                Comment(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = SpaceLarge,
                            vertical = SpaceSmall
                        ),
                    comment = Comment(
                        username = "Philip Lackner $it",
                        comment = "Lorem Ipsum is simply dummy text of the printing and typesetting industry\" +\n" +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s"
                    )
                )
            }
        }
    }

}

@Composable
fun Comment(
    modifier: Modifier,
    comment: Comment = Comment(),
    onLikeClick: (Boolean) -> Unit = {}
) {
    Card(
        modifier = modifier,
        elevation = 5.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceMedium)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.philipp),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(ProfilePictureSizeExtraSmall)
                    )
                    Spacer(modifier = Modifier.width(SpaceSmall))
                    Text(
                        text = comment.username,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body2,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Text(
                    text = "2 days ago",
                    color = MaterialTheme.colors.onBackground,
                    style = MaterialTheme.typography.body2
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = comment.comment,
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier.weight(9f)
                )
                Spacer(modifier = Modifier.width(SpaceSmall))
                IconButton(
                    onClick = {
                        onLikeClick(comment.isLiked)
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = if (comment.isLiked) {
                            stringResource(id = R.string.txt_unlike)
                        } else {
                            stringResource(id = R.string.txt_like)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(SpaceMedium))

            Text(
                text = stringResource(id = R.string.like_by_x_peoper, comment.likeCount),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.body2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}


/*
@Preview
@Composable
fun PostDetailPreview() {
    PostDetailScreen(
        navController = rememberNavController(),
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
}*/
