package com.example.setsiscase.data.remote.dto

import com.example.setsiscase.domain.model.CategoryModelUI

data class Category(
    val categoryName: String,
    val createdDate: String,
    val id: Int,
    val products: Any
){
    fun toCategoryModelUI(): CategoryModelUI{
        return CategoryModelUI(
            categoryName=categoryName,
            createdDate=createdDate,
            id=id
        )
    }
}

