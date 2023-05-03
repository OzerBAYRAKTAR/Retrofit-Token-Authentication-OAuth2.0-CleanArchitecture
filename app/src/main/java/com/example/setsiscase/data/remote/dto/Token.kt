package com.example.setsiscase.data.remote.dto

data class Token(
    val accessToken: String ,
    val expiration: String,
    val refreshToken: String
)