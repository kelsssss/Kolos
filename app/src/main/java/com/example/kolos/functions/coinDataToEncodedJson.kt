package com.example.kolos.functions

import com.example.kolos.network.CoinData
import com.google.gson.Gson
import java.net.URLEncoder

fun coinDataToEncodedJson(coin: CoinData): String {
    val gson = Gson()
    val coinDataJson = gson.toJson(coin)
    return URLEncoder.encode(coinDataJson, "UTF-8")
}