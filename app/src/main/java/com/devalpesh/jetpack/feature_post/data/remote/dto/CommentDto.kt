package com.devalpesh.jetpack.feature_post.data.remote.dto

import com.devalpesh.jetpack.core.domain.models.Comment
import java.text.SimpleDateFormat
import java.util.Locale

data class CommentDto(
    val id: String,
    val username: String,
    val profilePictureUrl: String,
    val timestamp: Long,
    val comment: String,
    val likeCount: Int,
    val isLiked: Boolean
){
    fun toComment() : Comment{
        return Comment(
            id = id,
            username = username,
            profileImageUrl = profilePictureUrl,
            formattedTime = SimpleDateFormat("MMM dd HH:mm", Locale.getDefault()).run {
                format(
                    timestamp
                )
            },
            comment = comment,
            isLiked = isLiked,
            likeCount = likeCount
        )
    }
}