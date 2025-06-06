package com.example.kolos.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kolos.data.CoinRepositoryImpl
import com.example.kolos.network.ApiService
import com.example.kolos.network.CoinDataById
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavouriteCoinDetailsViewModel @Inject constructor(
//    private val apiService: ApiService,
    private val repository: CoinRepositoryImpl,
) : ViewModel() {
    private var _coinData = MutableStateFlow<CoinDataById?>(null)
    val coinData: StateFlow<CoinDataById?> = _coinData

    fun getCoinInfoById(coinId: String) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
//                val response = apiService.getCoinDataById(coinId)
                val response = repository.getCoinDataById(coinId)
                _coinData.value = response
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}