package com.devalpesh.jetpack.feature_post.domain.use_case

data class PostUseCases(
     val getPostsForFollowsUseCase: GetPostForFollowsUseCase,
     val createPostUseCase: CreatePostUseCase,
     val getPostDetails : GetPostDetailsUseCase,
     val getCommentsForPost : GetCommentsForPostUseCase,
     val createComment : CreateCommentUseCase,
     val toggleLikeForParent : ToggleLikeForParentUserUseCase,
     val getLikesForParent : GetLikeParentUseCase
)