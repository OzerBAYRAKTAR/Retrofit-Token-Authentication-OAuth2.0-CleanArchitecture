package com.example.setsiscase.presentation.login.model

sealed interface TextFieldStatusModel {
    data class Success(val data: String? = null) : TextFieldStatusModel
    data class Error(val error: String? = null) : TextFieldStatusModel
    object Loading : TextFieldStatusModel
}