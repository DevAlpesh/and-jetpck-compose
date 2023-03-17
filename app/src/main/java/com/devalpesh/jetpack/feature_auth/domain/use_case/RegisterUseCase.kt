package com.devalpesh.jetpack.feature_auth.domain.use_case

import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_auth.domain.repository.AuthRepository

class RegisterUseCase(
    private val repository: AuthRepository
) {

    suspend operator fun invoke(
        email: String,
        username: String,
        password: String
    ): SimpleResources {
        return repository.register(email.trim(), username.trim(), password.trim())
    }

}