package com.devalpesh.jetpack.core.data.repository

import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.feature_post.data.remote.PostApi
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_post.data.paging.PostSource
import com.devalpesh.jetpack.feature_profile.data.remote.ProfileApi
import com.devalpesh.jetpack.feature_profile.data.remote.request.FollowUpdateRequest
import com.devalpesh.jetpack.feature_profile.domain.model.Profile
import com.devalpesh.jetpack.feature_profile.domain.model.Skill
import com.devalpesh.jetpack.feature_profile.domain.model.UpdateProfileData
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException

class ProfileRepositoryImpl(
    private val profileApi: ProfileApi,
    private val postApi: PostApi,
    private val gson: Gson
) : ProfileRepository {

    override fun getPostPaged(userId: String): Flow<PagingData<Post>> {
        return Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            PostSource(postApi, PostSource.Source.Profile(userId))
        }.flow
    }
//645a343d652ef937262061dc
    override suspend fun getProfile(userId: String): Resource<Profile> {
        return try {
            val response = profileApi.getProfile(userId)
            if (response.success) {
                Resource.Success(response.data?.toProfile())
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun getSkills(): Resource<List<Skill>> {
        return try {
            val response = profileApi.getSkills()
            Resource.Success(
                data = response.map { it.toSkill() }
            )
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun updateProfile(
        updateProfileData: UpdateProfileData,
        bannerImageUri: Uri?,
        profilePictureUri: Uri?
    ): SimpleResources {

        val bannerFile = bannerImageUri?.toFile()
        val profilePictureFile = profilePictureUri?.toFile()

        return try {
            val response = profileApi.updateProfile(
                bannerImage = bannerFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "banner_image",
                            bannerFile.name,
                            bannerFile.asRequestBody()
                        )
                },
                profilePicture = profilePictureFile?.let {
                    MultipartBody.Part
                        .createFormData(
                            "profile_picture",
                            profilePictureFile.name,
                            profilePictureFile.asRequestBody()
                        )
                },
                updateProfileData = MultipartBody.Part
                    .createFormData(
                        "update_profile_data",
                        gson.toJson(updateProfileData)
                    )
            )
            if (response.success) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun searchUser(query: String): Resource<List<UserItem>> {
        return try {
            val response = profileApi.searchUser(query)
            Resource.Success(
                data = response.map { it.toUserItem() }
            )
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun followUser(userId: String): SimpleResources {
        return try {
            val response = profileApi.followUser(
                request = FollowUpdateRequest(userId)
            )
            if (response.success){
                Resource.Success(Unit)
            }else{
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun unfollowUser(userId: String): SimpleResources {
        return try {
            val response = profileApi.unfollowUser(
                userId = userId
            )
            if (response.success){
                Resource.Success(Unit)
            }else{
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }
}