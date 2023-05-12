package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class CreateCommentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String, comment: String): SimpleResources {
        if (comment.isBlank()) {
            return Resource.Error(UiText.StringResource(R.string.txt_error_field_empty))
        }
        if (postId.isBlank()){
            return Resource.Error(UiText.unknownError())
        }
        return repository.createComment(postId, comment)
    }
}