package com.devalpesh.jetpack.feature_profile.data.remote

import com.devalpesh.jetpack.core.data.dto.reponse.BasicApiResponse
import com.devalpesh.jetpack.core.data.dto.reponse.UserItemDto
import com.devalpesh.jetpack.feature_profile.data.remote.request.FollowUpdateRequest
import com.devalpesh.jetpack.feature_profile.data.remote.response.ProfileResponse
import com.devalpesh.jetpack.feature_profile.data.remote.response.SkillDto
import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Query

interface ProfileApi {

    @GET("api/user/profile")
    suspend fun getProfile(
        @Query("userid") userId: String
    ): BasicApiResponse<ProfileResponse>

    @GET("api/skills/get")
    suspend fun getSkills(): List<SkillDto>

    @Multipart
    @PUT("api/user/update")
    suspend fun updateProfile(
        @Part bannerImage: MultipartBody.Part?,
        @Part profilePicture: MultipartBody.Part?,
        @Part updateProfileData: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("api/user/search")
    suspend fun searchUser(
        @Query("query") query: String
    ): List<UserItemDto>

    @POST("api/following/follow")
    suspend fun followUser(
        @Body request: FollowUpdateRequest
    ): BasicApiResponse<Unit>

    @DELETE("api/following/unfollow")
    suspend fun unfollowUser(
        @Query("userId") userId : String
    ): BasicApiResponse<Unit>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }

}