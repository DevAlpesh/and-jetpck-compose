package com.devalpesh.jetpack.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.devalpesh.jetpack.BuildConfig
import com.devalpesh.jetpack.core.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharePref(app: Application): SharedPreferences {
        return app.getSharedPreferences(
            Constants.SHARED_PREF_NAME,
            MODE_PRIVATE
        )
    }

    @Provides
    @Singleton
    fun provideJwtToken(sharedPreferences: SharedPreferences): String {
        return sharedPreferences.getString(Constants.KEY_JWT_TOKEN, "") ?: ""
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(token: String): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor {
                val modifiedRequest =
                    it.request().newBuilder().addHeader("Authorization", "Bearer $token")
                        .build()
                it.proceed(modifiedRequest)
            }.also { client ->
                if (BuildConfig.DEBUG) {
                    val logging = HttpLoggingInterceptor()
                    logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                    client.addInterceptor(logging)
                }
            }
            .build()
    }

}