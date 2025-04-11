package com.example.kolos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolos.data.FavouriteCoinRepositoryImpl
import com.example.kolos.database.CoinsDatabase
import com.example.kolos.database.FavouriteCoin
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
//class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {
class FavouriteCoinViewModel @Inject constructor(
//    private val db: CoinsDatabase,
    private val repository: FavouriteCoinRepositoryImpl
) : ViewModel() {

    //    private val db = CoinsDatabase.Companion.getInstance(application)
//    private val dbDao = db.coinsDao()

    val allCoins = repository.allCoins
//    val allCoins = dbDao.getAll()

    fun insertCoin(coin: FavouriteCoin) {
        viewModelScope.launch {
            repository.insertCoin(coin)
//            dbDao.insertCoin(coin)
        }
    }

    fun deleteCoin(id: String) {
        viewModelScope.launch {
            repository.deleteCoin(id)
//            dbDao.deleteCoin(id)
        }
    }

    suspend fun isCoinFavourite(name: String): Int {
        return repository.isCoinFavourite(name)
//        return dbDao.isCoinFavourite(name)
    }

    fun deleteAllCoins() {
        viewModelScope.launch {
            repository.deleteAllCoins()
        }
    }


}