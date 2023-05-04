package com.devalpesh.jetpack.feature_profile.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.core.util.toPx
import com.devalpesh.jetpack.feature_profile.domain.model.Skill

@Composable
fun BannerSection(
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    iconSize: Dp = 35.dp,
    leftIconModifier: Modifier = Modifier,
    rightIconModifier: Modifier = Modifier,
    topSkills: List<Skill> = emptyList(),
    bannerUrl: String? = null,
    shouldShowGithub: Boolean = false,
    shouldShowInstagram: Boolean = false,
    shouldShowLinkedIn: Boolean = false,
    onGitHubClick: () -> Unit = {},
    onInstagramClick: () -> Unit = {},
    onLinkedInClick: () -> Unit = {},
) {
    BoxWithConstraints(
        modifier = modifier
    ) {
        Image(
            painter = rememberAsyncImagePainter(bannerUrl),
            contentDescription = stringResource(id = R.string.txt_banner_image),
            contentScale = ContentScale.Crop,
            modifier = imageModifier
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
            modifier = leftIconModifier
                .height(iconSize)
                .align(Alignment.BottomStart)
                .padding(SpaceSmall)
        ) {
            topSkills.forEach { skillUrl ->
                Spacer(modifier = Modifier.width(SpaceSmall))
                Image(
                    painter = rememberAsyncImagePainter(
                        skillUrl.imageUrl
                    ),
                    contentDescription = null,
                    modifier = Modifier.height(iconSize)
                )
            }
        }

        Row(
            modifier = rightIconModifier
                .height(iconSize)
                .align(Alignment.BottomEnd)
                .padding(SpaceSmall)
        ) {
            if (shouldShowGithub) {
                IconButton(
                    onClick = onGitHubClick,
                    modifier = Modifier.size(iconSize)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_github_icon_1),
                        contentDescription = "GitHub",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
            if (shouldShowInstagram) {
                IconButton(
                    onClick = onInstagramClick,
                    modifier = Modifier.size(iconSize)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_instagram_glyph_1),
                        contentDescription = "Instagram",
                        modifier = Modifier.size(iconSize)
                    )
                }
            }
            if (shouldShowLinkedIn) {
                IconButton(
                    onClick = onLinkedInClick,
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
}

