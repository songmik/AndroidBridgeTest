package com.example.androidbridgetest.api


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("api_response")
    val apiResponse: ApiResponse?,
    @SerializedName("result")
    val result: String?,
    @SerializedName("review_result")
    val reviewResult: ReviewResult?
)