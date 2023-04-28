package com.example.setsiscase.data.source.api

import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.util.Constants
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface SetsisApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("/api/Auth/Login")
     fun login(@Body loginRequest: LoginRequest
    ): Call<LoginResponse>

    @GET(Constants.CATEGORY_URL)
    suspend fun getAllCategories(): CategoryModel


    @GET(Constants.RANDOMPRODUCTS_URL)
    suspend fun getRandomProducts(): ProductModel

    @GET("/api/Product/GetByCategoryId")
    @Headers("accept: */*")
    suspend fun getProductsByCategoryId(
        @Query("CategoryId") categoryId: Int,
        @Query("pageNumber") pageNumber: List<Int>
    ): ProductModel


    @Headers(
        "Content-Type: application/json"
    )
    @FormUrlEncoded
    @POST("/api/Auth/RefreshTokenLogin")
    suspend fun refreshAccessToken(
        @Field("refreshToken") refreshToken: String
    ): Response<LoginResponse>
}