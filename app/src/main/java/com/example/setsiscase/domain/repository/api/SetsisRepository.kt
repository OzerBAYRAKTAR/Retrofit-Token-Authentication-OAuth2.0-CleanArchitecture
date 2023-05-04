package com.example.setsiscase.domain.repository.api



import com.example.setsiscase.data.remote.dto.*
import retrofit2.Call
import retrofit2.Response

interface  SetsisRepository {

    fun login(request: LoginRequest): Call<LoginResponse>
    suspend fun getAllCategories(): CategoryModel
    suspend fun getRandomProducts(): ProductModel
    suspend fun getProductsByCategoryId(categoryId: Int,pageNumber: Int): Response<ProductModel>

}