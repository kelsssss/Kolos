package com.example.kolos

import androidx.lifecycle.ViewModel
import com.example.kolos.network.CoinsData
import com.example.kolos.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel(){

    private val _coinsData = MutableStateFlow<List<CoinsData>?>(null)
    val coinsData: StateFlow<List<CoinsData>?> = _coinsData

    fun fetchCoinsData(){
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getCoinsData()
                _coinsData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

}