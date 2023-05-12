package com.devalpesh.jetpack.core.domain.models

data class Comment(
    val id: String,
    val username: String,
    val profileImageUrl: String,
    val formattedTime: String,
    val comment: String,
    val likeCount: Int,
    val isLiked: Boolean
)
