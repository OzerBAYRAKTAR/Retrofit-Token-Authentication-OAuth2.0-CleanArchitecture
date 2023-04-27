package com.example.setsiscase.presentation.product

import com.example.setsiscase.domain.model.ProductModelUI

data class ProductListState(
    val isLoading: Boolean = false,
    val infoList: List<ProductModelUI> = emptyList(),
    val error: String = ""
)