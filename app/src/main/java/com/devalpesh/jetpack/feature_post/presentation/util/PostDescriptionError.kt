package com.devalpesh.jetpack.feature_post.presentation.util

import com.devalpesh.jetpack.core.domain.util.AppError

sealed class PostDescriptionError : AppError(){
    object FieldEmpty : PostDescriptionError()
}
