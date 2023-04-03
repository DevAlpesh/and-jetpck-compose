package com.devalpesh.jetpack.feature_post.domain.use_case

import android.net.Uri
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class CreatePostUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        description: String,
        imageUri: Uri
    ): SimpleResources {
        return repository.createPost(description, imageUri)
    }
}