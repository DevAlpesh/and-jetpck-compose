package com.devalpesh.jetpack.feature_post.data.remote

import com.devalpesh.jetpack.core.data.dto.reponse.BasicApiResponse
import com.devalpesh.jetpack.core.data.dto.reponse.UserItemDto
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.feature_post.data.remote.dto.CommentDto
import com.devalpesh.jetpack.feature_post.data.remote.request.CreateCommentRequest
import com.devalpesh.jetpack.feature_post.data.remote.request.LikeUpdateRequest
import okhttp3.MultipartBody
import retrofit2.http.*

interface PostApi {

    @GET("/api/post/get")
    suspend fun getPostsForFollows(
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    @GET("/api/user/post")
    suspend fun getPostForProfile(
        @Query("userId") userId: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): List<Post>

    @Multipart
    @POST("/api/post/create")
    suspend fun createPost(
        @Part postData: MultipartBody.Part,
        @Part postImage: MultipartBody.Part
    ): BasicApiResponse<Unit>

    @GET("/api/post/details")
    suspend fun getPostDetails(
        @Query("postId") postId: String
    ): BasicApiResponse<Post>

    @GET("api/comment/get")
    suspend fun getCommentsForPost(
        @Query("postId") postId: String
    ): List<CommentDto>

    @POST("api/comment/create")
    suspend fun createComment(
        @Body request: CreateCommentRequest
    ): BasicApiResponse<Unit>

    @POST("api/like")
    suspend fun likeParent(
        @Body request: LikeUpdateRequest
    ): BasicApiResponse<Unit>

    @DELETE("api/unlike")
    suspend fun unlikeParent(
        @Query("parentId") parentId: String,
        @Query("parentType") parentType: Int
    ): BasicApiResponse<Unit>

    @GET("/api/like/parent")
    suspend fun getLikesForParent(
        @Query("parentId") parentId : String
    ) : List<UserItemDto>

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }

}