package com.devalpesh.jetpack.feature_post.data.repository

import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_post.data.data_source.remote.PostApi
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {
    override suspend fun getPostsForFollows(page: Int, pageSize: Int): Resource<List<Post>> {
        return try {
            val posts = api.getPostsForFollows(page, pageSize)
            Resource.Success(posts)
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