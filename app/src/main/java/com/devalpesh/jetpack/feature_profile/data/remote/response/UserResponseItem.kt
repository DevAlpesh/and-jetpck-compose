package com.devalpesh.jetpack.feature_profile.data.remote.response

import com.devalpesh.jetpack.feature_profile.domain.model.UserItem


data class UserResponseItem(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean,
) {
    fun toUserItem(): UserItem {
        return UserItem(userId, username, profilePictureUrl, bio, isFollowing)
    }
}