package com.example.kolos.data

import com.example.kolos.network.ApiService
import com.example.kolos.network.CoinData
import com.example.kolos.network.CoinDataById
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    var apiService: ApiService
): CoinRepository {
    override suspend fun getAllCoins(): List<CoinData> {
        return apiService.getCoinsData()
    }

    override suspend fun getCoinDataById(coinId: String): CoinDataById {
        return apiService.getCoinDataById(coinId)
    }
}