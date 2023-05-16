package com.devalpesh.jetpack.feature_post.presentation.util

import com.devalpesh.jetpack.core.util.AppError

sealed class PostDescriptionError : AppError(){
    object FieldEmpty : PostDescriptionError()
}
