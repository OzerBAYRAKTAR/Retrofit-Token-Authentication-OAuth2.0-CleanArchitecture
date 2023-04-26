package com.example.setsiscase.presentation.home

import com.example.setsiscase.domain.model.CategoryModelUI

data class HomeListState(
    val isLoading: Boolean = false,
    val coins: List<CategoryModelUI> = emptyList(),
    val error: String = ""
)