package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class ToggleLikeForParentUserUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        parentId: String,
        parentType: Int,
        isLiked: Boolean
    ): SimpleResources {
        return if (isLiked) {
            repository.unlikeParent(
                parentId = parentId,
                parentType = parentType
            )
        } else {
            repository.likeParent(
                parentId = parentId, parentType = parentType
            )
        }
    }
}