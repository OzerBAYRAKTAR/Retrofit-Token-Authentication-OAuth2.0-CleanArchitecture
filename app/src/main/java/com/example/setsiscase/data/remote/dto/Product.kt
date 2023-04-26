package com.example.setsiscase.data.remote.dto

data class Product(
    val category: Any,
    val categoryId: Int,
    val createdDate: String,
    val id: Int,
    val price: Double,
    val productName: String,
    val stock: Int
)