package com.devalpesh.jetpack.di

import com.devalpesh.jetpack.feature_post.data.remote.PostApi
import com.devalpesh.jetpack.feature_post.data.repository.PostRepositoryImpl
import com.devalpesh.jetpack.feature_post.domain.respository.PostRepository
import com.devalpesh.jetpack.feature_post.domain.use_case.CreateCommentUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.CreatePostUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.GetCommentsForPostUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.GetLikeParentUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.GetPostDetailsUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.GetPostForFollowsUseCase
import com.devalpesh.jetpack.feature_post.domain.use_case.PostUseCases
import com.devalpesh.jetpack.feature_post.domain.use_case.ToggleLikeForParentUserUseCase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PostApi.BASE_URL)
            .client(client)
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi,
        gson: Gson
    ): PostRepository {
        return PostRepositoryImpl(api, gson)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollowsUseCase = GetPostForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository),
            getPostDetails = GetPostDetailsUseCase(repository),
            getCommentsForPost = GetCommentsForPostUseCase(repository),
            createComment = CreateCommentUseCase(repository),
            toggleLikeForParent = ToggleLikeForParentUserUseCase(repository),
            getLikesForParent = GetLikeParentUseCase(repository)
        )
    }
}