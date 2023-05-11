package com.devalpesh.jetpack.feature_activity.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Activity
import com.devalpesh.jetpack.core.presentation.components.StandardToolbar
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceMedium

@Composable
fun ActivityScreen(
    navigateUp: () -> Unit = {},
    viewModel: ActivityViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val activities = viewModel.activities.collectAsLazyPagingItems()

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            StandardToolbar(
                navigateUp = navigateUp,
                title = {
                    Text(
                        text = stringResource(id = R.string.txt_your_activity),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                },
                modifier = Modifier.fillMaxWidth(),
                showBackArrow = false,
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                contentPadding = PaddingValues(SpaceMedium)
            ) {
                items(activities) { activity ->
                    activity?.let {
                        ActivityItem(
                            activity = Activity(
                                userId = activity.userId,
                                parentId = activity.parentId,
                                username = activity.username,
                                activityType = activity.activityType,
                                formattedTime = activity.formattedTime
                            ),
                        )
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

