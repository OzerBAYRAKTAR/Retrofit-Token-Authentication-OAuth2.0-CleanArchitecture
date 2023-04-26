package com.example.setsiscase.data.source

import com.example.setsiscase.data.remote.dto.ProductModel
import com.example.setsiscase.domain.source.RemoteDataSource

class RemoteDataSourceImp(
private val remoteService: SetsisApi
): RemoteDataSource {
    override suspend fun getRandomProducts(): ProductModel {
        return remoteService.getRandomProducts()
    }
}