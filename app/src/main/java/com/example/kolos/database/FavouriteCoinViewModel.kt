package com.example.kolos.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {

    private val db = CoinsDatabase.getInstance(application)
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



}