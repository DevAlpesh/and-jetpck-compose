package com.devalpesh.jetpack.feature_profile.presentation.profile

sealed class ProfileEvent {
    data class GetProfile(val userId : String) : ProfileEvent()
    data class LikePost(val postId : String) : ProfileEvent()
}