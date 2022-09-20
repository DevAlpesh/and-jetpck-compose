package com.devalpesh.jetpack.presentation.createpost

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.presentation.components.StandardTextField
import com.devalpesh.jetpack.presentation.components.StandardToolbar
import com.devalpesh.jetpack.presentation.postdetail.CreatePostViewModel
import com.devalpesh.jetpack.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.presentation.util.states.StandardTextFieldStates

@Composable
fun CreatePostScreen(
    navController: NavController,
    viewModel: CreatePostViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        StandardToolbar(
            navController = navController,
            showBackArrow = true,
            title = {
                Text(
                    text = stringResource(id = R.string.txt_create_new_post),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )
            },
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(16f / 9f)
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colors.onBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clickable {

                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.txt_choose_image),
                    tint = MaterialTheme.colors.onBackground
                )
            }
            Spacer(modifier = Modifier.height(SpaceMedium))
            StandardTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.descriptionState.value.text,
                singleLine = false,
                maxLines = 5,
                maxLength = 200,
                hint = stringResource(id = R.string.txt_description),
                error = viewModel.descriptionState.value.error,
                onValueChange = {
                    viewModel.setDescriptionState(
                        StandardTextFieldStates(text = it)
                    )
                }
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(
                    text = stringResource(id = R.string.txt_post),
                    color = MaterialTheme.colors.onPrimary
                )
                Spacer(modifier = Modifier.width(SpaceSmall))
                Icon(imageVector = Icons.Default.Send, contentDescription = null)
            }
        }
    }
}