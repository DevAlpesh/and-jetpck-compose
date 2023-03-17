package com.devalpesh.jetpack.core.domain.states

import com.devalpesh.jetpack.core.domain.util.AppError

data class StandardTextFieldStates(
    val text: String = "",
    val error: AppError? = null
)