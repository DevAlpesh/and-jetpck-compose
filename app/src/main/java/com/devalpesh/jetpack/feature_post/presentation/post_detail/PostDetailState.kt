package com.devalpesh.jetpack.feature_post.presentation.post_detail

import com.devalpesh.jetpack.core.domain.models.Comment
import com.devalpesh.jetpack.core.domain.models.Post

data class PostDetailState(
    val post: Post? = null,
    val isLoadingComments : Boolean = false,
    val comments : List<Comment> = emptyList(),
    val isLoadingPost: Boolean = false,
)
