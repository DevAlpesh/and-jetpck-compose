package com.devalpesh.jetpack.feature_post.domain.use_case

import androidx.paging.PagingData
import com.devalpesh.jetpack.core.domain.models.Post
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository
import kotlinx.coroutines.flow.Flow

class GetPostForFollowsUseCase(
    private val repository: PostRepository
) {
    suspend operator fun invoke(
        page:Int,
        pageSize:Int = Constants.DEFAULT_PAGE_SIZE,
    ): Resource<List<Post>> {
        return repository.getPostForFollows(page,pageSize)
    }
}