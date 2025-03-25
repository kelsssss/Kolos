package com.example.kolos

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {

    private val _coinData = MutableStateFlow<List<CoinData>?>(null)
    val coinData: StateFlow<List<CoinData>?> = _coinData

    var isLoading = MutableStateFlow(false)

    fun fetchCoinsData() {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitClient.apiService.getCoinsData()
                _coinData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}