package com.devalpesh.jetpack.feature_post.presentation.post_detail

sealed class PostDetailsEvent {
    object LikePost : PostDetailsEvent()
    object Comment : PostDetailsEvent()
    data class LikeComment(val commentId: String) : PostDetailsEvent()
    object SharePost : PostDetailsEvent()
    data class EnteredComment(val comment: String) : PostDetailsEvent()
}
