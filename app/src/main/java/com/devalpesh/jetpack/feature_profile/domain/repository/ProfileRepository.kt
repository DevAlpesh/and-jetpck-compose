package com.devalpesh.jetpack.feature_profile.domain.repository

import android.net.Uri
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_profile.domain.model.Profile
import com.devalpesh.jetpack.feature_profile.domain.model.Skill
import com.devalpesh.jetpack.feature_profile.domain.model.UpdateProfileData

interface ProfileRepository {
    suspend fun getProfile(userId: String): Resource<Profile>

    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?,
    ) : SimpleResources
}