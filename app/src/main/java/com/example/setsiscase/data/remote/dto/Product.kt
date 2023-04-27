package com.example.setsiscase.data.remote.dto

import com.example.setsiscase.domain.model.ProductModelUI

data class Product(
    val category: Any,
    val categoryId: Int,
    val createdDate: String,
    val id: Int,
    val price: Double,
    val productName: String,
    val stock: Int
){
    fun toProductModelUI(): ProductModelUI{
    return ProductModelUI(
        categoryId=categoryId,
        id=id,
        price=price,
        productName=productName,
        stock=stock
    )
}

}




