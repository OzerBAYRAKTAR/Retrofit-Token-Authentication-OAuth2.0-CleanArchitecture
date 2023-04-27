package com.example.setsiscase.presentation.category

import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI

data class CategoryListState(
    val isLoading: Boolean = false,
    val infoList: List<CategoryModelUI> = emptyList(),
    val error: String = ""
)