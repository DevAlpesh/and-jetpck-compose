package com.devalpesh.jetpack.feature_profile.data.remote

import com.devalpesh.jetpack.core.data.dto.reponse.BasicApiResponse
import com.devalpesh.jetpack.feature_profile.data.remote.response.ProfileResponse
import com.devalpesh.jetpack.feature_profile.data.remote.response.SkillDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileApi {

    @GET("api/user/profile")
    suspend fun getProfile(
        @Query("userid") userId: String
    ): BasicApiResponse<ProfileResponse>

    @GET("api/skills/get")
    suspend fun getSkills() : List<SkillDto>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }

}