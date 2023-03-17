package com.devalpesh.jetpack.feature_auth.domain.repository

import com.devalpesh.jetpack.core.util.SimpleResources

interface AuthRepository {
    suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResources
}