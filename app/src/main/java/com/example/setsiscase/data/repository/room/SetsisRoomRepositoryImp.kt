package com.example.setsiscase.data.repository.room

import androidx.lifecycle.LiveData
import com.example.setsiscase.data.remote.dto.Product
import com.example.setsiscase.data.source.RoomDb.SetsisDao
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository

class SetsisRoomRepositoryImp(
    private val dao: SetsisDao
) : SetsisRoomRepository {
    override fun getAllProducts(): List<ProductModelUI> {
        return  dao.getAllProducts()
    }

    override suspend fun upsert(product: ProductModelUI) {
        return dao.upsert(product)
    }

    override suspend fun delete(product: ProductModelUI) {
        return dao.delete(product)
    }
}