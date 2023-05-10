package com.devalpesh.jetpack.feature_activity.presentation.activity.componenents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Activity
import com.devalpesh.jetpack.core.domain.util.ActivityAction
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall

@Composable
fun ActivityItem(
    activity: Activity,
    modifier: Modifier = Modifier,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        backgroundColor = MaterialTheme.colors.onSurface,
        elevation = 5.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(SpaceSmall),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val fillerText = when (activity.actionType) {
                is ActivityAction.LikedPost -> {
                    stringResource(id = R.string.txt_liked)
                }
                is ActivityAction.CommentedOnPost -> {
                    stringResource(id = R.string.txt_commented_on)
                }
                is ActivityAction.FollowedYou -> {
                    stringResource(id = R.string.txt_followed_you)
                }
            }
            val actionText = when (activity.actionType) {
                is ActivityAction.LikedPost -> {
                    stringResource(id = R.string.txt_your_post)
                }
                is ActivityAction.CommentedOnPost -> {
                    stringResource(id = R.string.txt_your_post)
                }
                is ActivityAction.FollowedYou -> ""
            }
            Text(
                text = buildAnnotatedString {
                    val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
                    withStyle(boldStyle) {
                        append(activity.username)
                        append(" ")
                    }
                    append(" $fillerText ")
                    withStyle(boldStyle) {
                        append(actionText)
                    }
                },
                fontSize = 12.sp,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = activity.formattedTime,
                textAlign = TextAlign.Right,
                fontSize = 12.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }
}