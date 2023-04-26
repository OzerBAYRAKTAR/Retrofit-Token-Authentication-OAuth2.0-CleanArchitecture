package com.example.setsiscase.data.mapper

import com.example.setsiscase.data.remote.dto.CategoryModel
import com.example.setsiscase.data.remote.dto.LoginRequest
import com.example.setsiscase.data.remote.dto.ProductModel
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.LoginRequestUI
import com.example.setsiscase.domain.model.ProductModelUI


fun CategoryModel.toCategoryModelUI():CategoryModelUI{
    return CategoryModelUI(
        categories=categories
    )
}
fun ProductModel.toProductModelUI():ProductModelUI{
    return ProductModelUI(
        products=products
    )
}
fun LoginRequest.toLoginRequestUI():LoginRequestUI{
    return LoginRequestUI(
        usernameOrEmail=usernameOrEmail,
        password=password
    )
}