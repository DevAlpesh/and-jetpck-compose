package com.devalpesh.jetpack.feature_activity.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devalpesh.jetpack.core.data.paging.ActivitySource
import com.devalpesh.jetpack.core.domain.models.Activity
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.feature_activity.data.remote.ActivityApi
import com.devalpesh.jetpack.feature_activity.domain.repository.ActivityRepository
import kotlinx.coroutines.flow.Flow

class ActivityRepositoryImpl(
    private val api: ActivityApi
) : ActivityRepository {

    override val activities: Flow<PagingData<Activity>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            ActivitySource(api)
        }.flow

}