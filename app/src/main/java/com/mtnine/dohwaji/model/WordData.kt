package com.mtnine.dohwaji.model

import com.google.gson.annotations.SerializedName

data class WordData(
    @SerializedName("statusCode")
    val statusCode: Int,
    @SerializedName("msg")
    val words: List<String>
)