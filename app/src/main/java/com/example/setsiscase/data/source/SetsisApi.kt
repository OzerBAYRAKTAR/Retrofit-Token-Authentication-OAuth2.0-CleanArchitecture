package com.example.setsiscase.data.source

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

    @GET("/api/Product/GetByCategoryId/{CategoryId}/{pageNumber}")
    @Headers("accept: */*")
    suspend fun getProductsByCategoryId(
        @Path("CategoryId") CategoryId: Int,
        @Path("pageNumber") pageNumber: Int
    ): ProductModel
}