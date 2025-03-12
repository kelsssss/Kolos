package com.example.kolos.network

import com.google.gson.annotations.SerializedName

data class CoinsData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price") val price: Double
)
