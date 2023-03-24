package com.devalpesh.jetpack.core.data.dto.reponse

data class BasicApiResponse<T>(
    val success: Boolean,
    val message: String? = "",
    val data: T? = null
)