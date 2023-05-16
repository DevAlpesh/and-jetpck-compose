package com.devalpesh.jetpack.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Comment
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.presentation.ui.theme.HintGray
import com.devalpesh.jetpack.core.presentation.ui.theme.MediumGray
import com.devalpesh.jetpack.core.presentation.ui.theme.ProfilePictureSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.core.presentation.ui.theme.TextWhite
import com.devalpesh.jetpack.core.util.Constants

@Composable
fun Post(
    post: Post,
    modifier: Modifier = Modifier,
    showProfileImage: Boolean = true,
    onPostClick: () -> Unit = {},
    onLikeClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(SpaceMedium)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .offset(
                    y = if (showProfileImage) {
                        ProfilePictureSizeMedium / 2f
                    } else 0.dp
                )
                .clip(MaterialTheme.shapes.medium)
                .shadow(5.dp)
                .background(MediumGray)
                .clickable {
                    onPostClick()
                }
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = post.imageUrl
                ),
                contentDescription = "Post Image",
                modifier = Modifier
                    .aspectRatio(16f / 9f)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceMedium)
            ) {
                ActionRow(
                    modifier = Modifier.fillMaxWidth(),
                    onLikeClick = onLikeClick,
                    onCommentClick = {

                    },
                    onShareClick = {

                    },
                    onUsernameClick = {

                    },
                    username = post.username,
                )
                Spacer(modifier = Modifier.height(SpaceSmall))
                Text(
                    text = buildAnnotatedString {
                        append(post.description)
                        withStyle(
                            SpanStyle(
                                color = HintGray
                            )
                        ) {
                            append(
                                " " + LocalContext.current.getString(R.string.txt_read_more)
                            )
                        }
                    },
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = Constants.MAX_POST_DESCRIPTION_LINES
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(
                            id = R.string.like_by_x_peoper,
                            post.likeCount
                        ),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h2,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = stringResource(
                            id = R.string.x_comment,
                            post.commentCount
                        ),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h2,
                        fontSize = 16.sp,
                    )
                }
            }
        }
        if (showProfileImage) {
            Image(
                painter = rememberAsyncImagePainter(model = post.profilePictureUrl),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(ProfilePictureSizeMedium)
                    .clip(CircleShape)
                    .align(Alignment.TopCenter)
            )
        }
    }
}

@Composable
fun EngagementButton(
    modifier: Modifier = Modifier,
    isLiked: Boolean = false,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    iconSize: Dp = 30.dp,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(
            onClick = onLikeClick,
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                tint = if (isLiked) {
                    MaterialTheme.colors.primary
                } else {
                    TextWhite
                },
                contentDescription = if (isLiked) {
                    stringResource(id = R.string.txt_unliked)
                } else {
                    stringResource(id = R.string.txt_like)
                }
            )
        }
        Spacer(modifier = Modifier.width(SpaceSmall))
        IconButton(
            onClick = {
                onCommentClick()
            },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Comment,
                contentDescription = stringResource(id = R.string.txt_comment)
            )
        }
        Spacer(modifier = Modifier.width(SpaceSmall))
        IconButton(
            onClick = {
                onShareClick()
            },
            modifier = Modifier.size(iconSize)
        ) {
            Icon(
                imageVector = Icons.Filled.Share,
                contentDescription = stringResource(id = R.string.txt_share)
            )
        }
    }
}

@Composable
fun ActionRow(
    modifier: Modifier,
    isLiked: Boolean = false,
    onLikeClick: () -> Unit,
    onCommentClick: () -> Unit,
    onShareClick: () -> Unit,
    onUsernameClick: (String) -> Unit,
    username: String,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier,
    ) {
        Text(
            text = username,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            ),
            modifier = Modifier
                .clickable {
                    onUsernameClick(username)
                }
        )
        EngagementButton(
            modifier = Modifier
                .width(100.dp),
            isLiked = isLiked,
            onLikeClick = onLikeClick,
            onCommentClick = onCommentClick,
            onShareClick = onShareClick
        )
    }
}

