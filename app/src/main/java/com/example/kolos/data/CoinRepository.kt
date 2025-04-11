package com.example.kolos.data

import com.example.kolos.network.CoinData
import com.example.kolos.network.CoinDataById

interface CoinRepository {
    suspend fun getAllCoins(): List<CoinData>
    suspend fun getCoinDataById(coinId: String): CoinDataById
}