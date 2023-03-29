package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class GetPostForFollowsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int
    ): Resource<List<Post>> {
        return repository.getPostsForFollows(page, pageSize)
    }
}