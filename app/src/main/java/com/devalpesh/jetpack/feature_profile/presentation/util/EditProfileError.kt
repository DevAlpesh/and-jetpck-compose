package com.devalpesh.jetpack.feature_profile.presentation.util

import com.devalpesh.jetpack.core.domain.util.AppError

sealed class EditProfileError : AppError(){
    object FieldEmpty : EditProfileError()
}
