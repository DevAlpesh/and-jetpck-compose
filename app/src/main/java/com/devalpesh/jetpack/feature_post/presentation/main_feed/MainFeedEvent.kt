package com.devalpesh.jetpack.feature_post.presentation.main_feed

sealed class MainFeedEvent{
    object LoadMorePosts : MainFeedEvent()
    object LoadedPage : MainFeedEvent()
    data class LikePost(val postId : String) : MainFeedEvent()

}