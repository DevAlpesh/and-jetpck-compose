package com.devalpesh.jetpack.core.util

import com.devalpesh.jetpack.core.domain.models.Post
import javax.inject.Inject

class DefaultPostLiker @Inject constructor() : PostLiker {
    override suspend fun toggleLike(
        posts: List<Post>,
        parentId: String,
        onRequest: suspend (isLiked: Boolean) -> SimpleResources,
        onStateUpdated: (List<Post>) -> Unit
    ) {
        val post = posts.find { it.id == parentId }
        val currentlyLiked = post?.isLiked == true
        val currentLikeCount = post?.likeCount ?: 0

        val newPost = posts.map { post ->
            if (post.id == parentId) {
                post.copy(
                    isLiked = !post.isLiked,
                    likeCount = if (currentlyLiked) {
                        post.likeCount - 1
                    } else post.likeCount + 1
                )
            } else post
        }

        onStateUpdated(newPost)
        when (onRequest(currentlyLiked)) {
            is Resource.Success -> Unit

            is Resource.Error -> {
                val oldPost = posts.map { post ->
                    if (post.id == parentId) {
                        post.copy(
                            isLiked = currentlyLiked,
                            likeCount = currentLikeCount
                        )
                    } else post
                }
                onStateUpdated(oldPost)
            }
        }
    }
}
