package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class GetPostDetailsUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<Post> {
        return repository.getPostDetails(postId)
    }
}