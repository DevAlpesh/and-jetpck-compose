package com.devalpesh.jetpack.feature_post.presentation.person_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PersonAdd
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.components.UserProfileItem
import com.devalpesh.jetpack.core.presentation.ui.theme.IconSizeMedium
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceLarge
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium
import com.devalpesh.jetpack.core.presentation.util.Screen
import com.devalpesh.jetpack.core.presentation.util.UiEvent
import com.devalpesh.jetpack.core.presentation.util.asString
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PersonListScreen(
    scaffoldState: ScaffoldState,
    onNavigate: (String) -> Unit = {},
    navigateUp: () -> Unit = {},
    viewModel: PersonListViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(event.uiText.asString(context))
                }

                else -> Unit
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StandardToolbar(
                navigateUp = navigateUp,
                showBackArrow = true,
                title = {
                    Text(
                        text = stringResource(id = R.string.txt_liked_by),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            )
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(SpaceLarge)
            ) {
                items(state.users) { user ->
                    UserProfileItem(
                        user = user,
                        actionIcon = {
                            Icon(
                                imageVector = if (user.isFollowing) {
                                    Icons.Default.PersonRemove
                                } else Icons.Default.PersonAdd,
                                contentDescription = null,
                                tint = MaterialTheme.colors.onBackground,
                                modifier = Modifier.size(IconSizeMedium)
                            )
                        },
                        onItemClick = {
                            onNavigate(Screen.ProfileScreen.route + "?userId=${user.userId}")
                        },
                        onActionItemClick = {
                            viewModel.onEvent(PersonListEvent.ToggleFollowStateForUser(user.userId))
                        },
                        ownUserId = viewModel.ownUserId.value
                    )
                    Spacer(modifier = Modifier.height(SpaceMedium))
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