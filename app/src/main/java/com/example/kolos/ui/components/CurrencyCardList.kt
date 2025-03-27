package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.viewmodels.MainViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.ui.theme.KolosTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyCardList(navController: NavController, coinData: List<CoinData>, viewModel: MainViewModel = viewModel()) {

    var isRefreshing by remember { mutableStateOf(false) }
    var isLoading = viewModel.isLoading.collectAsState()

    LaunchedEffect(isLoading.value) {
        if(!isLoading.value){
            isRefreshing = false
        }
    }


    PullToRefreshBox(
        isRefreshing = isRefreshing,
        onRefresh = {
            isRefreshing = true
            viewModel.viewModelScope.launch {
                viewModel.fetchCoinsData()
            }

        }
    ) {
        LazyColumn {
            items(coinData) { coinData ->
                CurrencyCard(navController, coinData)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun CurrencyCardListPreview() {
    KolosTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
//            CurrencyCardList(modifier = Modifier)
        }
    }
}