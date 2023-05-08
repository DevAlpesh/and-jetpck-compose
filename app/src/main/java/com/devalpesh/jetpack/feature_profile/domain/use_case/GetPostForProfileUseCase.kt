package com.devalpesh.jetpack.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetPostForProfileUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(userId : String) : Flow<PagingData<Post>> {
        return repository.getPostPaged(userId)
    }
}