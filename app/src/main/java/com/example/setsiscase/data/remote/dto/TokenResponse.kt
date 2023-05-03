package com.example.setsiscase.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TokenResponse(

    @SerializedName("refreshToken")
    val refreshToken: String
)

