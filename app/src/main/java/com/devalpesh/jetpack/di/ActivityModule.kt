package com.devalpesh.jetpack.di

import com.devalpesh.jetpack.feature_activity.data.ActivityRepositoryImpl
import com.devalpesh.jetpack.feature_activity.data.remote.ActivityApi
import com.devalpesh.jetpack.feature_activity.domain.repository.ActivityRepository
import com.devalpesh.jetpack.feature_activity.domain.use_case.GetActivitiesUseCase
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
class ActivityModule {

    @Provides
    @Singleton
    fun provideActivityApi(client: OkHttpClient): ActivityApi {
        return Retrofit.Builder()
            .baseUrl(ActivityApi.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ActivityApi::class.java)
    }

    @Provides
    @Singleton
    fun provideActivityRepository(api: ActivityApi): ActivityRepository {
        return ActivityRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideGetActivityUseCase(repository: ActivityRepository): GetActivitiesUseCase {
        return GetActivitiesUseCase(repository)
    }
}