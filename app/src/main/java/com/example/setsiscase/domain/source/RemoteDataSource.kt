package com.example.setsiscase.domain.source

import com.example.setsiscase.data.remote.dto.ProductModel

interface RemoteDataSource {

    suspend fun getRandomProducts(): ProductModel
}