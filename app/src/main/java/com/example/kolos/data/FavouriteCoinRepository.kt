package com.example.kolos.data

import com.example.kolos.database.CoinsDao
import com.example.kolos.database.FavouriteCoin
import kotlinx.coroutines.flow.Flow

interface FavouriteCoinRepository {
    val allCoins:  Flow<List<FavouriteCoin>>
    suspend fun insertCoin(coin: FavouriteCoin): Unit
    suspend fun deleteCoin(id: String): Unit
    suspend fun isCoinFavourite(name: String): Int
    suspend fun deleteAllCoins(): Unit
}