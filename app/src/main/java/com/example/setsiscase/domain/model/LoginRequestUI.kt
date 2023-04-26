package com.example.setsiscase.domain.model

import com.google.gson.annotations.SerializedName

data class LoginRequestUI (

    var usernameOrEmail:String,
    var password:String
    )