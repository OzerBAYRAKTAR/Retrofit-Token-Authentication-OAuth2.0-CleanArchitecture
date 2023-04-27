package com.example.setsiscase.data.source.api

import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface SetsisApi {

    @Headers(
        "Content-Type: application/json"
    )
    @POST("/api/Auth/Login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

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
}