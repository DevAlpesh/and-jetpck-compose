package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_profile.domain.repository.ProfileRepository

class ToggleFollowStateForUserUseCase(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(userId: String, isFollowing: Boolean): SimpleResources {
        return if (isFollowing) {
            repository.unfollowUser(userId)
        } else {
            repository.followUser(userId)
        }
    }
}