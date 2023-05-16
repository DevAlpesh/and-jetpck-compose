package com.devalpesh.jetpack.core.domain.states

import com.devalpesh.jetpack.core.util.AppError

data class StandardTextFieldStates(
    val text: String = "",
    val error: AppError? = null
)