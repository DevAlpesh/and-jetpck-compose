package com.devalpesh.jetpack.feature_post.domain.use_case

import android.net.Uri
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class CreatePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        description: String,
        imageUri: Uri?
    ): SimpleResources {
        if (imageUri == null){
            return Resource.Error(
                uiText = UiText.StringResource(
                    R.string.txt_error_no_image_picked
                )
            )
        }
        if (description.isBlank()) {
            return Resource.Error(
                uiText = UiText.StringResource(
                    R.string.txt_error_description_blank
                )
            )
        }
        return repository.createPost(description, imageUri)
    }
}