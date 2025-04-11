package com.example.kolos.functions

import com.example.kolos.network.CoinData
import com.google.gson.Gson
import java.net.URLDecoder

fun encodedJsonToCoin(encodedCoinDataJson: String?): CoinData {
    val gson = Gson()
    val coinDataJson = URLDecoder.decode(encodedCoinDataJson, "UTF-8")
    return gson.fromJson(coinDataJson, CoinData::class.java)
}