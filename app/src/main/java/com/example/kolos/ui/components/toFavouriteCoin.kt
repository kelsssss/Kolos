package com.example.kolos.ui.components

import com.example.kolos.database.FavouriteCoin
import com.example.kolos.network.CoinData

fun toFavouriteCoin(coinData: CoinData): FavouriteCoin{
    return FavouriteCoin(
        id = coinData.id,
        image = coinData.image,
        name = coinData.name,
        symbol = coinData.name
    )
}