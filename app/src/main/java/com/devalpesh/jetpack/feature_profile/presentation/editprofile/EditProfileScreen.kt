package com.devalpesh.jetpack.feature_profile.presentation.editprofile

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.states.StandardTextFieldStates
import com.devalpesh.jetpack.core.presentation.components.Chip
import com.devalpesh.jetpack.core.presentation.components.StandardTextField
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.ui.theme.ProfilePictureSizeLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.feature_profile.presentation.util.EditProfileError
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import kotlin.random.Random

@Composable
fun EditProfileScreen(
    navController: NavController,
    profilePictureSize: Dp = ProfilePictureSizeLarge,
    viewModel: EditProfileViewModel = hiltViewModel()
) {

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        StandardToolbar(navController = navController, showBackArrow = true, navActions = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = stringResource(id = R.string.txt_save_changes),
                    tint = MaterialTheme.colors.onBackground
                )
            }
        }, title = {
            Text(
                text = stringResource(id = R.string.txt_edit_your_profile),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            BannerEditSection(
                bannerImage = painterResource(id = R.drawable.channelart),
                profileImage = painterResource(id = R.drawable.philipp),
                profilePictureSize = profilePictureSize,
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(SpaceLarge)
            ) {
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.usernameState.value.text,
                    hint = stringResource(id = R.string.txt_username),
                    error = when (viewModel.usernameState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.txt_this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.setUsernameState(
                            StandardTextFieldStates(text = it)
                        )
                    },
                    leadingIcon = Icons.Default.Person
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.githubTextFieldState.value.text,
                    hint = stringResource(id = R.string.txt_github_profile_url),
                    error = when (viewModel.githubTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.txt_this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.setUsernameState(
                            StandardTextFieldStates(text = it)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_github_icon_1)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.instagramTextFieldState.value.text,
                    hint = stringResource(id = R.string.txt_instagram_profile_url),
                    error = when (viewModel.instagramTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.txt_this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.setInstagramTextFieldState(
                            StandardTextFieldStates(text = it)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_instagram_glyph_1)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.linkedInTextFieldState.value.text,
                    hint = stringResource(id = R.string.txt_linked_in_profile_url),
                    error = when (viewModel.linkedInTextFieldState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.txt_this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.setLinkedInTextFieldState(
                            StandardTextFieldStates(text = it)
                        )
                    },
                    leadingIcon = ImageVector.vectorResource(id = R.drawable.ic_linkedin_icon_1)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                StandardTextField(
                    modifier = Modifier.fillMaxWidth(),
                    text = viewModel.bioState.value.text,
                    singleLine = false,
                    maxLines = 3,
                    maxLength = 200,
                    hint = stringResource(id = R.string.txt_your_bio),
                    error = when (viewModel.bioState.value.error) {
                        is EditProfileError.FieldEmpty -> stringResource(
                            id = R.string.txt_this_field_cant_be_empty
                        )
                        else -> ""
                    },
                    onValueChange = {
                        viewModel.setBioState(
                            StandardTextFieldStates(text = it)
                        )
                    },
                    leadingIcon = Icons.Default.Description
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                Text(
                    text = stringResource(id = R.string.txt_select_top_3_skills),
                    style = MaterialTheme.typography.h2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(SpaceMedium))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    mainAxisAlignment = MainAxisAlignment.Center,
                    mainAxisSpacing = SpaceMedium,
                    crossAxisSpacing = SpaceMedium
                ) {
                    listOf(
                        "Kotlin",
                        "JavaScript",
                        "Assembly",
                        "C++",
                        "C",
                        "C#",
                        "Dart",
                        "TypeScript",
                        "Python",
                    ).forEach {
                        Chip(
                            text = it,
                            selected = Random.nextInt(2) == 0,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BannerEditSection(
    bannerImage: Painter,
    profileImage: Painter,
    onBannerClick: () -> Unit = {},
    onProfileImageClick: () -> Unit = {},
    profilePictureSize: Dp = ProfilePictureSizeLarge
) {
    val bannerHeight = (LocalConfiguration.current.screenWidthDp / 2.5f).dp
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(bannerHeight + profilePictureSize / 2f)
    ) {
        Image(
            painter = bannerImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(bannerHeight)
        )
        Image(
            painter = profileImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(profilePictureSize)
                .clip(CircleShape)
                .border(
                    width = 1.dp, color = MaterialTheme.colors.onSurface, shape = CircleShape
                )
        )
    }
}