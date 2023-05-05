package com.devalpesh.jetpack.feture_search.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.devalpesh.jetpack.core.domain.models.User
import com.devalpesh.jetpack.core.domain.states.StandardTextFieldStates
import com.devalpesh.jetpack.core.presentation.components.StandardTextField
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.components.UserProfileItem
import com.devalpesh.jetpack.core.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.util.Screen

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
                            userId = "644f924fcaee957d0f992dc3",
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
                        },
                        onItemClick = {
                            navController.navigate(
                                Screen.ProfileScreen.route+"?userId=644f924fcaee957d0f992dc3"
                            )
                        },
                        onActionItemClick = {

                        }
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
                }
            }
        }
    }
}