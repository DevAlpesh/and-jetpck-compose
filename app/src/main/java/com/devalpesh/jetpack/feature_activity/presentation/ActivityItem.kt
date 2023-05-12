package com.devalpesh.jetpack.feature_activity.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Activity
import com.devalpesh.jetpack.core.domain.util.ActivityType
import com.devalpesh.jetpack.core.presentation.ui.theme.SpaceSmall
import com.devalpesh.jetpack.core.presentation.util.Screen

@Composable
fun ActivityItem(
    activity: Activity,
    onNavigate: (String) -> Unit = {},
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
            val fillerText = when (activity.activityType) {
                is ActivityType.LikedPost -> {
                    stringResource(id = R.string.txt_liked)
                }

                is ActivityType.CommentedOnPost -> {
                    stringResource(id = R.string.txt_commented_on)
                }

                is ActivityType.FollowedUser -> {
                    stringResource(id = R.string.txt_followed_you)
                }

                is ActivityType.LikedComment -> {
                    stringResource(id = R.string.txt_liked)
                }
            }
            val actionText = when (activity.activityType) {
                is ActivityType.LikedPost -> {
                    stringResource(id = R.string.txt_your_post)
                }

                is ActivityType.CommentedOnPost -> {
                    stringResource(id = R.string.txt_your_post)
                }

                is ActivityType.FollowedUser -> ""
                is ActivityType.LikedComment -> {
                    stringResource(id = R.string.txt_your_comment)
                }
            }
            val annotatedText = buildAnnotatedString {
                val boldStyle = SpanStyle(fontWeight = FontWeight.Bold)
                pushStringAnnotation(
                    tag = "username",
                    annotation = "username"
                )
                withStyle(boldStyle) {
                    append(activity.username)
                }
                pop()
                append(" $fillerText ")

                pushStringAnnotation(
                    tag = "parent",
                    annotation = "parent"
                )
                withStyle(boldStyle) {
                    append(actionText)
                }
            }
            ClickableText(
                text = annotatedText,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = MaterialTheme.colors.onBackground
                ),
                onClick = { offset ->
                    annotatedText.getStringAnnotations(
                        tag = "username",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {annotaion->
                        onNavigate(
                            Screen.ProfileScreen.route + "?userId=${activity.userId}"
                        )
                    }

                    annotatedText.getStringAnnotations(
                        tag = "parent",
                        start = offset,
                        end = offset
                    ).firstOrNull()?.let {annotaion->
                        onNavigate(
                            Screen.PostDetailScreen.route + "/${activity.parentId}"
                        )
                    }
                }
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