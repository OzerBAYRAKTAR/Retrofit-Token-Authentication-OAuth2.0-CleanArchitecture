package com.example.setsiscase.data.source.api

import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.remote.dto.TokenResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TokenRefreshApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("/api/Auth/RefreshTokenLogin")
    suspend fun refreshAccessToken(
        @Body refreshToken: TokenResponse
    ): Response<LoginResponse>
}