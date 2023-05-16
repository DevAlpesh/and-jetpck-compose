package com.devalpesh.jetpack.feature_profile.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.User
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.presentation.components.StandardTextField
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.components.UserProfileItem
import com.devalpesh.jetpack.core.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.util.Screen
import timber.log.Timber

@Composable
fun SearchScreen(
    navigateUp: () -> Unit = {},
    onNavigate: (String) -> Unit = {},
    viewModel: SearchViewModel = hiltViewModel()
) {
    val state = viewModel.searchState.value
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            StandardToolbar(navigateUp = navigateUp, showBackArrow = true, title = {
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
                    text = viewModel.searchFieldState.value.text,
                    hint = stringResource(id = R.string.txt_search),
                    onValueChange = {
                        viewModel.onEvent(SearchEvent.Query(it))
                    },
                    leadingIcon = Icons.Default.Search
                )
                Spacer(modifier = Modifier.height(SpaceLarge))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.userItem) { user ->
                        UserProfileItem(
                            user = user,
                            actionIcon = {
                                Icon(
                                    imageVector = if (user.isFollowing) {
                                        Icons.Default.PersonRemove
                                    } else {
                                        Icons.Default.PersonAdd
                                    },
                                    contentDescription = null,
                                    modifier = Modifier.size(IconSizeMedium),
                                    tint = Color.White,
                                )
                            },
                            onItemClick = {
                                onNavigate(
                                    Screen.ProfileScreen.route + "?userId=${user.userId}"
                                )
                            },
                            onActionItemClick = {
                                viewModel.onEvent(SearchEvent.ToggleFollowState(user.userId))
                            }
                        )
                        Spacer(modifier = Modifier.height(SpaceMedium))
                    }
                }
            }
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}