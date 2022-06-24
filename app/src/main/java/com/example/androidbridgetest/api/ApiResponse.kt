package com.example.androidbridgetest.api


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("result_code")
    val resultCode: String?,
    @SerializedName("result_message")
    val resultMessage: String?
)