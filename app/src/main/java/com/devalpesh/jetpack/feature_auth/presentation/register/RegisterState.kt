package com.devalpesh.jetpack.feature_auth.presentation.register

import com.devalpesh.jetpack.core.util.UiText

data class RegisterState(
    val successful: Boolean? = null,
    val message: UiText? = null,
    val isLoading: Boolean = false
)
