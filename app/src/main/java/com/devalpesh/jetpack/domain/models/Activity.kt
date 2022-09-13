package com.devalpesh.jetpack.domain.models

import com.devalpesh.jetpack.domain.util.ActivityAction

data class Activity(
   val username: String,
   val actionType: ActivityAction,
   val formattedTime: String,
)
