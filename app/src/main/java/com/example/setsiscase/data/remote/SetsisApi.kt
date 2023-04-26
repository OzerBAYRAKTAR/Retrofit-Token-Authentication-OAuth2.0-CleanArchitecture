package com.example.setsiscase.data.remote

import com.example.setsiscase.data.remote.dto.CategoryModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.remote.dto.ProductModel
import com.example.setsiscase.util.Constants
import retrofit2.Call
import retrofit2.http.*

interface SetsisApi {

    @Headers(
        "accept: */*",
        "Content-Type: application/json"
    )
    @POST(Constants.LOGIN_URL)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(Constants.CATEGORY_URL)
    suspend fun getAllCategories(): List<CategoryModel>

    @Headers("accept: */*")
    @GET(Constants.RANDOMPRODUCTS_URL)
    suspend fun getRandomProducts(): Call<List<ProductModel>>

    @GET("/api/Product/GetByCategoryId/{CategoryId}/{pageNumber}")
    @Headers("accept: */*")
    suspend fun getProductsByCategoryId(
        @Path("CategoryId") CategoryId: Int,
        @Path("pageNumber") pageNumber: Int
    ): List<ProductModel>
}