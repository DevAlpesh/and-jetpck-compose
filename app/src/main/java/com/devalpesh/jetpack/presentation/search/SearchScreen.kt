package com.devalpesh.jetpack.presentation.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.domain.models.User
import com.devalpesh.jetpack.presentation.components.StandardTextField
import com.devalpesh.jetpack.presentation.components.StandardToolbar
import com.devalpesh.jetpack.presentation.components.UserProfileItem
import com.devalpesh.jetpack.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.presentation.util.states.StandardTextFieldStates

@Composable
fun SearchScreen(
    navController: NavController, viewModel: SearchViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        StandardToolbar(navController = navController, showBackArrow = true, title = {
            Text(
                text = stringResource(id = R.string.txt_search_for_user),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceLarge)
        ) {
            StandardTextField(
                modifier = Modifier.fillMaxWidth(),
                text = viewModel.searchState.value.text,
                hint = stringResource(id = R.string.txt_search),
                error = viewModel.searchState.value.error,
                onValueChange = {
                    viewModel.setSearchTextState(
                        StandardTextFieldStates(text = it)
                    )
                },
                leadingIcon = Icons.Default.Search
            )
            Spacer(modifier = Modifier.height(SpaceLarge))
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(10) {
                    UserProfileItem(
                        user = User(
                            profilePictureUrl = "",
                            username = "Philipp Lackner",
                            description = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed\n" +
                                    "diam nonumy eirmod tempor invidunt ut labore et dolore \n" +
                                    "magna aliquyam erat, sed diam voluptua",
                            followerCount = 234,
                            followingCount = 534,
                            postCount = 65
                        ),
                        actionIcon = {
                            Icon(
                                imageVector = Icons.Default.PersonAdd,
                                contentDescription = null,
                                modifier = Modifier.size(IconSizeMedium)
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
        }
    }
}