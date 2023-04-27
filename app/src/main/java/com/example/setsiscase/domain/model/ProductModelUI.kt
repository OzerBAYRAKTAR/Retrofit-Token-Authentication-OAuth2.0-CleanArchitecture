package com.example.setsiscase.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favorites")
data class ProductModelUI (
    val categoryId: Int?,

    @PrimaryKey
    val id: Int,

    val price: Double?,
    val productName: String?,
    val stock: Int?
    )
