package com.devalpesh.jetpack.feature_post.domain.respository

import android.net.Uri
import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Comment
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostRepository {
    val posts: Flow<PagingData<Post>>

    suspend fun createPost(description: String, imageUri : Uri): SimpleResources

    suspend fun getPostDetails(postId : String) : Resource<Post>

    suspend fun getCommentsForPost(postId: String) : Resource<List<Comment>>

    suspend fun createComment(postId : String,comment:String) : SimpleResources

    suspend fun likeParent(parentId : String,parentType : Int) : SimpleResources
    suspend fun unlikeParent(parentId : String,parentType : Int) : SimpleResources

}