package com.example.kolos.network

import com.google.gson.annotations.SerializedName

data class CoinDataById(
    val id: String,
    val symbol: String,
    val name: String,
    val image: Image,
    @SerializedName("market_data") val marketData: MarketData,

)

data class Image(
    val small: String
)

data class MarketData(
    @SerializedName("current_price") val price: CurrentPrice,
    @SerializedName("sparkline_7d") val sparkline: SparkLine
)

data class CurrentPrice(
    val usd: Double
)

data class SparkLine(
    val price: List<Double>
)
