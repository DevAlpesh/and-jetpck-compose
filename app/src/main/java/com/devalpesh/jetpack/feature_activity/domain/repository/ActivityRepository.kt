package com.devalpesh.jetpack.feature_activity.domain.repository

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Activity
import kotlinx.coroutines.flow.Flow

interface ActivityRepository {

    val activities: Flow<PagingData<Activity>>

    suspend fun getActivities(){

    }
}