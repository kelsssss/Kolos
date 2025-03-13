package com.example.kolos

import androidx.lifecycle.ViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private val _coinData = MutableStateFlow<List<CoinData>?>(null)
    val coinData: StateFlow<List<CoinData>?> = _coinData

    fun fetchCoinsData(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getCoinsData()
                _coinData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}