package com.example.setsiscase.data.repository


import com.example.setsiscase.data.remote.dto.*
import com.example.setsiscase.data.source.SetsisApi
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import retrofit2.Call
import kotlin.math.log


class SetsisRepositoryImp(
    private val api:SetsisApi
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

    override suspend fun getProductsByCategoryId(CategoryId: Int, pageNumber: Int): ProductModel {
        return api.getProductsByCategoryId(CategoryId,pageNumber)
    }


}