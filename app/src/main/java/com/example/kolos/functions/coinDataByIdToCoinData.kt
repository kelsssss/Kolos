package com.example.kolos.functions

import com.example.kolos.network.CoinData
import com.example.kolos.network.CoinDataById
import com.example.kolos.network.SparklineData

fun coinDataByIdToCoinData(coinById: CoinDataById) : CoinData {
    return CoinData(
        id = coinById.id,
        symbol = coinById.symbol,
        name = coinById.name,
        image = coinById.image.small,
        price = coinById.marketData.price.usd,
        sparkline = SparklineData(price = coinById.marketData.sparkline.price)
    )
}