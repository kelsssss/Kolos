package com.example.kolos.data

import com.example.kolos.database.CoinsDatabase
import com.example.kolos.database.FavouriteCoin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class FavouriteCoinRepositoryImpl @Inject constructor(
    private val db: CoinsDatabase
): FavouriteCoinRepository {

    val dao = db.coinsDao()
    override val allCoins: Flow<List<FavouriteCoin>>
        get() = dao.getAll()

    override suspend fun deleteAllCoins() {
        dao.deleteAllCoins()
    }

    override suspend fun deleteCoin(id: String) {
        dao.deleteCoin(id)
    }

    override suspend fun insertCoin(coin: FavouriteCoin) {
        dao.insertCoin(coin)
    }

    override suspend fun isCoinFavourite(name: String): Int {
        return dao.isCoinFavourite(name)
    }
}