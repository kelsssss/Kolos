package com.example.kolos.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kolos.network.CoinDataById
import com.example.kolos.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavouriteCoinDetailsViewModel : ViewModel() {
    private var _coinData = MutableStateFlow<CoinDataById?>(null)
    val coinData: StateFlow<CoinDataById?> = _coinData

    fun getCoinInfoById(coinId: String){
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = RetrofitClient.apiService.getCoinDataById(coinId)
                _coinData.value = response
            } catch(e: Exception) {
                e.printStackTrace()
            }
        }
    }
}