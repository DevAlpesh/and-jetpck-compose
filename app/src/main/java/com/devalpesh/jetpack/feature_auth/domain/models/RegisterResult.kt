package com.devalpesh.jetpack.feature_auth.domain.models

import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_auth.presentation.util.AuthError

data class RegisterResult(
    val emailError: AuthError? = null,
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val result: SimpleResources? = null
)
