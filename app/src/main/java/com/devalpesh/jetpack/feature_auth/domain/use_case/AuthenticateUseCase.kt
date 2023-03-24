package com.devalpesh.jetpack.feature_auth.domain.use_case

import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_auth.domain.repository.AuthRepository

class AuthenticateUseCase(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(): SimpleResources {
        return repository.authenticate()
    }
}