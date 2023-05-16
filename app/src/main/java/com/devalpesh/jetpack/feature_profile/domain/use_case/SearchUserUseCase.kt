package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository

class SearchUserUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(query: String): Resource<List<UserItem>> {
        if (query.isBlank()) {
            return Resource.Success(data = emptyList())
        }
        return repository.searchUser(query)
    }
}