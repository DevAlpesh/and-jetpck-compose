package com.devalpesh.jetpack.feature_post.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.feature_post.data.data_source.paging.PostSource
import com.devalpesh.jetpack.feature_post.data.data_source.remote.PostApi
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class PostRepositoryImpl(
    private val api: PostApi
) : PostRepository {

    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(pageSize = Constants.PAGE_SIZE_POST)) {
            PostSource(api)
        }.flow
}










