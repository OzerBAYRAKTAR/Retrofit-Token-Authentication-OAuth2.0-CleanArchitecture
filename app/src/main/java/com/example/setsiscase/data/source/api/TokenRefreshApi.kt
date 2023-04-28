package com.example.setsiscase.data.source.api

import com.example.setsiscase.data.remote.dto.LoginResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface TokenRefreshApi {

    @FormUrlEncoded
    @POST("/api/Auth/RefreshTokenLogin")
    suspend fun refreshAccessToken(
        @Field("refreshToken") refreshToken: String
    ): LoginResponse
}