package com.example.setsiscase.domain.repository.room

import androidx.lifecycle.LiveData
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.domain.model.ProductModelUI


interface SetsisRoomRepository {

    fun getAllProducts(): List<ProductModelUI>

    suspend fun upsert(product: ProductModelUI)

    suspend fun delete(product: ProductModelUI)
}