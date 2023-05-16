package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_profile.domain.model.Profile
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository

class GetProfileUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String): Resource<Profile> {
        return repository.getProfile(userId)
    }
}