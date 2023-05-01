package com.example.setsiscase.presentation.login.model

import com.example.setsiscase.data.remote.dto.LoginResponse


sealed interface LoginResponseModel {
    data class Success(val data: LoginResponse) : LoginResponseModel
    data class Error(val error: String) : LoginResponseModel
    object Loading : LoginResponseModel
}
