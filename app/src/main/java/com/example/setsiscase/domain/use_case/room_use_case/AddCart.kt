package com.example.setsiscase.domain.use_case.room_use_case

import com.example.setsiscase.domain.model.InvalidFavoriteException
import com.example.setsiscase.domain.model.ProductModelUI
import com.example.setsiscase.domain.repository.room.SetsisRoomRepository

class AddCart(
    private val repository: SetsisRoomRepository
) {
    @Throws(InvalidFavoriteException::class)
    suspend operator fun invoke(product: ProductModelUI) {

        repository.upsert(product)
    }
}