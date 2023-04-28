package com.example.setsiscase.data.repository.api


import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.data.source.api.SetsisApi
import com.example.setsiscase.domain.repository.api.SetsisRepository
import retrofit2.Call
import retrofit2.Response


class SetsisRepositoryImp(
    private val api: SetsisApi
): SetsisRepository {
    override fun login(request: LoginRequest): Call<LoginResponse> {
        return api.login(request)
    }

    override suspend fun getAllCategories(): CategoryModel {
        return api.getAllCategories()
    }

    override suspend fun getRandomProducts(): ProductModel {
        return api.getRandomProducts()
    }

    override suspend fun getProductsByCategoryId(categoryId: Int,pageNumber:List<Int>): ProductModel {
        return api.getProductsByCategoryId(categoryId,pageNumber)
    }

    override suspend fun refreshAccessToken(refreshToken: String): Response<LoginResponse> {
        return api.refreshAccessToken(refreshToken)
    }


}