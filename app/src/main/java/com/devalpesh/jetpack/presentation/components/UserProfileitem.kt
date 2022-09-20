package com.devalpesh.jetpack.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.domain.models.User
import com.devalpesh.jetpack.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.presentation.ui.theme.ProfilePictureSizeSmall
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.ui.theme.SpaceSmall

@Composable
fun UserProfileItem(
    user: User,
    modifier: Modifier = Modifier,
    actionIcon: @Composable () -> Unit = {},
    onActionItemClick: () -> Unit = {}
) {

    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
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
                painter = painterResource(id = R.drawable.philipp),
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
            ) {
                Text(
                    text = user.username,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                Text(
                    text = user.description,
                    style = MaterialTheme.typography.body2,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
            Spacer(modifier = Modifier.width(SpaceSmall))
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