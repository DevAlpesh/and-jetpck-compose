package com.devalpesh.jetpack.feature_profile.domain.use_case

import com.devalpesh.jetpack.core.domain.repository.ProfileRepository

class LogoutUseCase(
    private val repository: ProfileRepository
) {
    operator fun invoke(){
        repository.logout()
    }
}