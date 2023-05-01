package com.example.setsiscase.data.source.api

import com.example.setsiscase.data.remote.dto.LoginResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TokenRefreshApi {

    @Headers(
        "Content-Type: application/json"
    )
    @FormUrlEncoded
    @POST("/api/Auth/RefreshTokenLogin")
    suspend fun refreshAccessToken(
        @Field("refreshToken") refreshToken: String
    ): LoginResponse
}