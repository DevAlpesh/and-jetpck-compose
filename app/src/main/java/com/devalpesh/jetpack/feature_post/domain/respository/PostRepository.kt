package com.devalpesh.jetpack.feature_post.domain.respository

import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.core.util.Resource

interface  PostRepository {
    suspend fun getPostsForFollows(
        page: Int = 0,
        pageSize: Int = Constants.PAGE_SIZE_POST
    ): Resource<List<Post>>
}