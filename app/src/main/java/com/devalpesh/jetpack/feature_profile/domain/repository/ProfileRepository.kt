package com.devalpesh.jetpack.feature_profile.domain.repository

import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_profile.domain.model.Profile
import com.devalpesh.jetpack.feature_profile.domain.model.Skill

interface ProfileRepository {
    suspend fun getProfile(userId: String) : Resource<Profile>

    suspend fun getSkills() : Resource<List<Skill>>
}