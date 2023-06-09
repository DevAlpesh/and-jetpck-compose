package com.devalpesh.jetpack.di

import android.content.SharedPreferences
import com.devalpesh.jetpack.feature_post.data.remote.PostApi
import com.devalpesh.jetpack.feature_profile.data.remote.ProfileApi
import com.devalpesh.jetpack.feature_profile.data.repository.GetSkillUseCase
import com.devalpesh.jetpack.core.data.repository.ProfileRepositoryImpl
import com.devalpesh.jetpack.core.domain.repository.ProfileRepository
import com.devalpesh.jetpack.core.domain.use_case.GetOwnUserIdUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.GetPostForProfileUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.GetProfileUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.ProfileUseCases
import com.devalpesh.jetpack.feature_profile.domain.use_case.SearchUserUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.SetSkillSelectedUseCase
import com.devalpesh.jetpack.core.domain.use_case.ToggleFollowStateForUserUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.LogoutUseCase
import com.devalpesh.jetpack.feature_profile.domain.use_case.UpdateProfileUseCase
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
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ProfileApi.BASE_URL)
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileApi: ProfileApi,
        postApi: PostApi,
        gson: Gson,
        sharedPreferences : SharedPreferences
    ): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, postApi, gson, sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideProfileUserCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            getSkills = GetSkillUseCase(repository),
            updateProfile = UpdateProfileUseCase(repository),
            setSkillSelected = SetSkillSelectedUseCase(),
            getPostsForProfile = GetPostForProfileUseCase(repository),
            searchUser = SearchUserUseCase(repository),
            toggleFollowStateForUser = ToggleFollowStateForUserUseCase(repository),
            logout = LogoutUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideToggleFollowStateForUserUseCase(
        repository: ProfileRepository
    ): ToggleFollowStateForUserUseCase {
        return ToggleFollowStateForUserUseCase(repository)
    }
}