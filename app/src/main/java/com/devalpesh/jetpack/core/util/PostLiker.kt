package com.devalpesh.jetpack.core.util

import com.devalpesh.jetpack.core.domain.models.Post

interface PostLiker {
    suspend fun toggleLike(
        posts: List<Post>,
        parentId: String,
        onRequest: suspend (isLiked : Boolean) -> SimpleResources,
        onStateUpdated: (List<Post>)->Unit
    )
}