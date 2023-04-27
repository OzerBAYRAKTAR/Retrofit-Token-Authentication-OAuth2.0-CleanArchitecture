package com.example.setsiscase.presentation.cart

import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI

data class CartListState(
    val isLoading: Boolean = false,
    val infoList: List<ProductModelUI> = emptyList(),
    val error: String = ""
)