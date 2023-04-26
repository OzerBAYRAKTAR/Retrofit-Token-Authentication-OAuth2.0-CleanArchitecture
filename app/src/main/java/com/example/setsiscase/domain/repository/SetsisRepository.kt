package com.example.setsiscase.domain.repository

import com.example.setsiscase.data.remote.dto.CategoryModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.LoginResponse
import com.example.setsiscase.data.remote.dto.ProductModel
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.Path
import javax.inject.Inject

interface SetsisRepository {

    fun login(request: LoginRequest): Call<LoginResponse>

    suspend fun getAllCategories(): List<CategoryModel>

    suspend fun getRandomProducts():Flow<Resource<ProductModelUI>>

    suspend fun getProductsByCategoryId(CategoryId: Int,
                                        pageNumber: Int): List<ProductModel>
}