package com.devalpesh.jetpack.core.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.ProfilePictureSizeSmall
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun UserProfileItem(
    user: UserItem,
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onItemClick: () -> Unit = {},
    onActionItemClick: () -> Unit = {},
    ownUserId: String = ""
) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        onClick = onItemClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    vertical = SpaceSmall,
                    horizontal = SpaceMedium
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .decoderFactory(SvgDecoder.Factory())
                        .data(user.profilePictureUrl)
                        .build()
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(ProfilePictureSizeSmall)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(SpaceSmall))
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.8f)
                    .weight(1f)
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = user.bio,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.width(SpaceSmall))
            if (user.userId != ownUserId) {
                IconButton(
                    onClick = onActionItemClick,
                    modifier = Modifier
                        .size(IconSizeMedium)
                ) {
                    actionIcon()
                }
            }
        }
    }
}