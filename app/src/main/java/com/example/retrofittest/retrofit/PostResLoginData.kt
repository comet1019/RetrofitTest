package com.example.retrofittest.retrofit

import com.google.gson.annotations.SerializedName

data class PostResLoginData(
    @SerializedName("code") val status: Int,
    @SerializedName("message") val message: String,
    @SerializedName("token") val token: String
)

