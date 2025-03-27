package com.example.kolos.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolos.database.CoinsDatabase
import com.example.kolos.database.FavouriteCoin
import kotlinx.coroutines.launch

class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = CoinsDatabase.Companion.getInstance(application)
    private val dbDao = db.coinsDao()

    val allCoins = dbDao.getAll()

    fun insertCoin(coin: FavouriteCoin) {
        viewModelScope.launch {
            dbDao.insertCoin(coin)
        }
    }

    fun deleteCoin(id: String) {
        viewModelScope.launch {
            dbDao.deleteCoin(id)
        }
    }

    suspend fun isCoinFavourite(name: String) : Int{
        return dbDao.isCoinFavourite(name)
    }



}