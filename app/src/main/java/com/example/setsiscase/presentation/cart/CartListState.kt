package com.example.setsiscase.presentation.cart

import androidx.lifecycle.LiveData
import com.example.setsiscase.domain.model.CategoryModelUI
import com.example.setsiscase.domain.model.ProductModelUI
import kotlinx.coroutines.flow.emptyFlow

data class CartListState(
    val isLoading: Boolean = false,
    val infoList: List<ProductModelUI> = emptyList(),
    val error: String = ""
)