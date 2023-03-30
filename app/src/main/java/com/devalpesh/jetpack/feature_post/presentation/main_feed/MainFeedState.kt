package com.devalpesh.jetpack.feature_post.presentation.main_feed


data class MainFeedState(
    val isLoadingFirstTime: Boolean = false,
    val isLoadingNewPosts: Boolean = false
)