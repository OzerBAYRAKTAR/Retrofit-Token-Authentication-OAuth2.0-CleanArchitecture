package com.example.setsiscase.data.remote.dto

import com.example.setsiscase.domain.model.LoginRequestUI
import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("usernameOrEmail")
    var usernameOrEmail:String,

    @SerializedName("password")
    var password:String
)

fun LoginRequest.toLoginRequestUI(): LoginRequestUI{
    return LoginRequestUI(
        usernameOrEmail=usernameOrEmail,
        password=password
    )
}