package com.devalpesh.jetpack.feature_profile.domain.use_case

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository
import com.devalpesh.jetpack.core.util.Resource
import kotlinx.coroutines.flow.Flow

class GetPostForProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String, page : Int): Resource<List<Post>> {
        return repository.getPostPaged(userId = userId, page = page)
    }
}