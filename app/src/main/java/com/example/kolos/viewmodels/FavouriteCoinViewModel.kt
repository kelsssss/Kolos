package com.example.kolos.viewmodels

import android.app.Application
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolos.database.CoinsDatabase
import com.example.kolos.database.FavouriteCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {
class FavouriteCoinViewModel @Inject constructor(
    private val db: CoinsDatabase
) : ViewModel() {

//    private val db = CoinsDatabase.Companion.getInstance(application)
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

    fun deleteAllCoins(){
        viewModelScope.launch {
            dbDao.deleteAllCoins()
        }
    }



}