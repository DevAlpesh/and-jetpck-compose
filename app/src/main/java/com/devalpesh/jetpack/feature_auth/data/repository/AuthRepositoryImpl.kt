package com.devalpesh.jetpack.feature_auth.data.repository

import android.content.SharedPreferences
import com.devalpesh.jetpack.R
import com.devalpesh.jetpack.core.util.Constants
import com.devalpesh.jetpack.core.util.Resource
import com.devalpesh.jetpack.core.util.SimpleResources
import com.devalpesh.jetpack.core.util.UiText
import com.devalpesh.jetpack.feature_auth.data.remote.request.CreateAccountRequest
import com.devalpesh.jetpack.feature_auth.data.remote.request.LoginRequest
import com.devalpesh.jetpack.feature_auth.data.remote.AuthApi
import com.devalpesh.jetpack.feature_auth.domain.repository.AuthRepository
import retrofit2.HttpException
import java.io.IOException

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val sharedPreferences: SharedPreferences
) : AuthRepository {
    override suspend fun register(
        email: String,
        username: String,
        password: String
    ): SimpleResources {
        val request = CreateAccountRequest(email, username, password)
        return try {
            val response = api.register(request)
            if (response.success) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun login(email: String, password: String): SimpleResources {
        val request = LoginRequest(email, password)
        return try {
            val response = api.login(request)
            if (response.success) {
                response.data?.token?.let { token ->
                    sharedPreferences.edit()
                        .putString(Constants.KEY_JWT_TOKEN, token)
                        .apply()
                }
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.txt_error_unknow))
            }
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }

    override suspend fun authenticate(): SimpleResources {
        return try {
            api.authenticate()
            Resource.Success(Unit)
        } catch (e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_couldnt_reach_server)
            )
        } catch (e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.txt_error_oops_something_went_wrong)
            )
        }
    }
}