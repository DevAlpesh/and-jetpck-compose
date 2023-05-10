package com.devalpesh.jetpack.feature_profile.domain.repository

import android.net.Uri
import androidx.paging.PagingData
import com.devalpesh.jetpack.core.data.dto.reponse.UserItemDto
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.feature_profile.domain.model.Profile
import com.devalpesh.jetpack.feature_profile.domain.model.Skill
import com.devalpesh.jetpack.feature_profile.domain.model.UpdateProfileData
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {


    fun getPostPaged(userId: String): Flow<PagingData<Post>>

    suspend fun getProfile(userId: String): Resource<Profile>


    suspend fun getSkills(): Resource<List<Skill>>

    suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?,
    ): SimpleResources

    suspend fun searchUser(query: String): Resource<List<UserItem>>

    suspend fun followUser(userId : String) : SimpleResources

    suspend fun unfollowUser(userId: String) : SimpleResources
}