package com.devalpesh.jetpack.core.data.dto.reponse

import com.devalpesh.jetpack.core.domain.models.UserItem

data class UserItemDto(
    val userId: String,
    val username: String,
    val profilePictureUrl: String,
    val bio: String,
    val isFollowing: Boolean,
){
    fun toUserItem() : UserItem{
        return UserItem(
            userId = userId,
            username = username,
            profilePictureUrl = profilePictureUrl,
            bio = bio,
            isFollowing = isFollowing
        )
    }
}