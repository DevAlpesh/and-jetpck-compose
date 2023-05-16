package com.devalpesh.jetpack.core.domain.models

import com.devalpesh.jetpack.core.util.ActivityType

data class Activity(
    val userId: String,
    val parentId: String,
    val username: String,
    val activityType: ActivityType,
    val formattedTime: String,
)
