package com.devalpesh.jetpack.feature_post.domain.respository

import android.net.Uri
import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Comment
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {

    suspend fun getPostForFollows(page: Int, pageSize: Int) : Resource<List<Post>>

    suspend fun createPost(description: String, imageUri: Uri): SimpleResources

    suspend fun getPostDetails(postId: String): Resource<Post>

    suspend fun getCommentsForPost(postId: String): Resource<List<Comment>>

    suspend fun createComment(postId: String, comment: String): SimpleResources

    suspend fun likeParent(parentId: String, parentType: Int): SimpleResources
    suspend fun unlikeParent(parentId: String, parentType: Int): SimpleResources

    suspend fun getLikesForParent(parentId: String): Resource<List<UserItem>>

}