package com.devalpesh.jetpack.core.domain.states

import com.devalpesh.jetpack.core.domain.util.AppError

data class PasswordTextFieldState(
    val text: String = "",
    val error: AppError? = null,
    val isPasswordVisible: Boolean = false
)