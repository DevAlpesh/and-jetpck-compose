package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.core.domain.models.Comment
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class GetCommentsForPostUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String): Resource<List<Comment>> {
        return repository.getCommentsForPost(postId)
    }
}