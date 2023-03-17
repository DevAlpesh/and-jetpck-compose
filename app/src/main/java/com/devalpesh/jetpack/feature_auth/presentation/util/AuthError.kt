package com.devalpesh.jetpack.feature_auth.presentation.util

import com.devalpesh.jetpack.core.domain.util.AppError

sealed class AuthError : AppError() {
    object FieldEmpty : AuthError()
    object InputTooShort : AuthError()
    object InvalidEmail : AuthError()
    object InvalidPassword : AuthError()
}