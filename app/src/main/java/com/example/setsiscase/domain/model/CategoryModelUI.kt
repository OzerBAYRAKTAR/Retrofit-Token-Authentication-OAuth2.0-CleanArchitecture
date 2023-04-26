package com.example.setsiscase.domain.model

import com.example.setsiscase.data.remote.dto.Category

data class CategoryModelUI (
    val categoryName: String,
    val createdDate: String,
    val id: Int,
    val products: Any
    )
