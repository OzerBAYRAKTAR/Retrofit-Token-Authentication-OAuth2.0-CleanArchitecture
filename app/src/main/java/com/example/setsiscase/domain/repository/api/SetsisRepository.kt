package com.example.setsiscase.domain.repository.api


import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import retrofit2.Call
import retrofit2.Response

interface  SetsisRepository {

    fun login(request: LoginRequest): Call<LoginResponse>
    suspend fun getAllCategories(): CategoryModel
    suspend fun getRandomProducts(): ProductModel
    suspend fun getProductsByCategoryId(categoryId: Int,pageNumber: List<Int>): ProductModel
    suspend fun refreshAccessToken(refreshToken: String): Response<LoginResponse>
}