package com.devalpesh.jetpack.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.util.toPx

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    iconSize: Dp = 30.dp,
    onGithubClicked: () -> Unit = {},
    onInstagramClicked: () -> Unit = {},
    onLinkedInClicked: () -> Unit = {}
) {
    BoxWithConstraints(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.channelart),
            contentDescription = stringResource(id = R.string.txt_banner_image),
            modifier = Modifier
                .fillMaxSize()
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black
                        ),
                        startY = constraints.maxHeight - iconSize.toPx() * 2f
                    )
                )
        )
        Row(
            modifier = Modifier
                .height(iconSize)
                .align(Alignment.BottomStart)
                .padding(SpaceSmall)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_js_logo),
                contentDescription = "JS",
                modifier = Modifier.height(iconSize)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Image(
                painter = painterResource(id = R.drawable.ic_csharp_logo),
                contentDescription = "C#",
                modifier = Modifier.height(iconSize)
            )
            Spacer(modifier = Modifier.width(SpaceMedium))
            Image(
                painter = painterResource(id = R.drawable.ic_kotlin_logo),
                contentDescription = "Kotlin",
                modifier = Modifier.height(iconSize)
            )
        }
        Row(
            modifier = Modifier
                .height(iconSize)
                .align(Alignment.BottomEnd)
                .padding(SpaceSmall)
        ) {
            IconButton(
                onClick = onGithubClicked,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_github_icon_1),
                    contentDescription = "Github",
                    modifier = Modifier.size(iconSize)
                )
            }
            IconButton(
                onClick = onInstagramClicked,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_instagram_glyph_1),
                    contentDescription = "Instagram",
                    modifier = Modifier.size(iconSize)
                )
            }
            IconButton(
                onClick = onLinkedInClicked,
                modifier = Modifier.size(iconSize)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_linkedin_icon_1),
                    contentDescription = "LinkedIn",
                    modifier = Modifier.size(iconSize)
                )
            }
        }
    }
}

