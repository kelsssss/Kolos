package com.example.kolos.network

import com.google.gson.annotations.SerializedName

data class CoinData(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    @SerializedName("current_price") val price: Double,
    @SerializedName("sparkline_in_7d") val sparkline: SparklineData
)

data class SparklineData(
    val price: List<Double>
)
