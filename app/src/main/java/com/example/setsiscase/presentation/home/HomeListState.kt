package com.example.setsiscase.presentation.home

import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI

data class HomeListState(
    val isLoading: Boolean = false,
    val infoList: List<ProductModelUI> = emptyList(),
    val error: String = ""
)