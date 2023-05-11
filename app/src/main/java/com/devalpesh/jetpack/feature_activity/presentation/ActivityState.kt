package com.devalpesh.jetpack.feature_activity.presentation

import com.devalpesh.jetpack.core.domain.models.Activity

data class ActivityState(
    val activities : List<Activity> = emptyList(),
    val isLoading : Boolean = false
)
