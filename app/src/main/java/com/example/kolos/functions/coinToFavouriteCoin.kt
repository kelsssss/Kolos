package com.example.kolos.functions

import com.example.kolos.database.FavouriteCoin
import com.example.kolos.network.CoinData

fun coinToFavouriteCoin(coinData: CoinData): FavouriteCoin {
    return FavouriteCoin(
        id = coinData.id,
        image = coinData.image,
        name = coinData.name,
        symbol = coinData.name
    )
}