package com.devalpesh.jetpack.feature_post.domain.use_case

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostForFollowsUseCase(
    private val repository: PostRepository
) {
    operator fun invoke(): Flow<PagingData<Post>> {
        return repository.posts
    }
}