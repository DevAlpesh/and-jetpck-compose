package com.devalpesh.jetpack.feature_post.domain.use_case

import com.devalpesh.jetpack.core.domain.models.UserItem
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository

class GetLikeParentUseCase(
   private val repository: PostRepository
){

    suspend operator fun invoke(parentId : String) : Resource<List<UserItem>>{
        return repository.getLikesForParent(parentId)
    }
}