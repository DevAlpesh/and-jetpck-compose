package com.devalpesh.jetpack.feature_auth.data.data_source.remote

import com.devalpesh.jetpack.core.data.dto.reponse.BasicApiResponse
import com.devalpesh.jetpack.feature_auth.data.data_source.remote.request.CreateAccountRequest
import com.devalpesh.jetpack.feature_auth.data.data_source.remote.request.LoginRequest
import com.devalpesh.jetpack.feature_auth.data.data_source.remote.response.AuthResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {

    @POST("/api/user/create")
    suspend fun register(
        @Body request: CreateAccountRequest
    ): BasicApiResponse<Unit>

    @POST("/api/user/login")
    suspend fun login(
        @Body request: LoginRequest
    ): BasicApiResponse<AuthResponse>

    @GET("/api/user/authenticate")
    suspend fun authenticate()

    companion object {
        const val BASE_URL = "http://10.0.2.2:8001/"
    }

}