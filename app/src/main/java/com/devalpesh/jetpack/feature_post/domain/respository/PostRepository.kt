package com.devalpesh.jetpack.feature_post.domain.respository

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import kotlinx.coroutines.flow.Flow

interface PostRepository {
    val posts: Flow<PagingData<Post>>
}