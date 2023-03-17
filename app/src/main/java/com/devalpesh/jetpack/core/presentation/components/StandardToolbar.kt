package com.devalpesh.jetpack.core.presentation.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.devalpesh.jetpack.R

/**
 * Application toolbar here
 * You can change configure as per your requirement
 */
@Composable
fun StandardToolbar(
    modifier: Modifier = Modifier,
    navController: NavController,
    showBackArrow: Boolean = true,
    navActions: @Composable RowScope.() -> Unit = {},
    title: @Composable () -> Unit,
) {
    TopAppBar(
        title = title,
        modifier = modifier,
        navigationIcon = if (showBackArrow) {
            {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(id = R.string.txt_back),
                        tint = MaterialTheme.colors.onBackground
                    )
                }
            }
        } else null,
        actions = navActions,
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 0.dp
    )
}