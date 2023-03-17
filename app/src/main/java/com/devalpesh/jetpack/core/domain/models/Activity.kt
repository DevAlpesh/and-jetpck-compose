package com.devalpesh.jetpack.core.domain.models

import com.devalpesh.jetpack.core.domain.util.ActivityAction

data class Activity(
    val username: String,
    val actionType: ActivityAction,
    val formattedTime: String,
)
