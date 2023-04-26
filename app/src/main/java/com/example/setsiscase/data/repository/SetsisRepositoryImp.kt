package com.example.setsiscase.data.repository

import com.example.setsiscase.data.remote.SetsisApi
import com.example.setsiscase.data.remote.dto.CategoryModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.remote.dto.ProductModel
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.SetsisRepository
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import javax.inject.Inject

class SetsisRepositoryImp @Inject constructor(
    private val api: SetsisApi
): SetsisRepository {
      override fun login(request: LoginRequest): Call<LoginResponse> {
        return api.login(request)
    }

    override suspend fun getAllCategories():List<CategoryModel> {
        return api.getAllCategories()
    }

    override suspend fun getRandomProducts(): Call<List<ProductModel>> {
        return api.getRandomProducts()
    }

    override suspend fun getProductsByCategoryId(
        CategoryId: Int,
        pageNumber: Int
    ): List<ProductModel> {
        return  api.getProductsByCategoryId(CategoryId,pageNumber)
    }


}