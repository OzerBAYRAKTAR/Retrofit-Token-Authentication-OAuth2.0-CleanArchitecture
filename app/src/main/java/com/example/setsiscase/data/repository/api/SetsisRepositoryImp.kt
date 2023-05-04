package com.example.setsiscase.data.repository.api


import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.data.source.api.SetsisApi
import com.example.setsiscase.domain.repository.api.SetsisRepository
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject


class SetsisRepositoryImp @Inject constructor(
    private val api: SetsisApi
): SetsisRepository {

    override suspend fun getAllCategories(): CategoryModel {
        return api.getAllCategories()
    }

    override suspend fun getRandomProducts(): ProductModel {
        return api.getRandomProducts()
    }

    override suspend fun getProductsByCategoryId(
        categoryId: Int,
        pageNumber: Int
    ): Response<ProductModel> {
        return api.getProductsByCategoryId(categoryId,pageNumber)
    }

    override  fun login(request: LoginRequest): Call<LoginResponse> {
        return api.login(request)
    }



}