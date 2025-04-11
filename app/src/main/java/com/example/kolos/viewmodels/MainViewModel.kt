package com.example.kolos.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kolos.data.CoinRepositoryImpl
import com.example.kolos.network.ApiService
import com.example.kolos.network.CoinData
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
//    private val apiService: ApiService,
    private val repository: CoinRepositoryImpl
) : ViewModel() {

    private val _coinData = MutableStateFlow<List<CoinData>?>(null)
    val coinData: StateFlow<List<CoinData>?> = _coinData

    var isLoading = MutableStateFlow(false)

    fun fetchCoinsData() {
        isLoading.value = true
//        CoroutineScope(Dispatchers.IO).launch {
        viewModelScope.launch {
            try {
//                val response = apiService.getCoinsData()
                val response = repository.getAllCoins()
                _coinData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                isLoading.value = false
            }
        }
    }
}