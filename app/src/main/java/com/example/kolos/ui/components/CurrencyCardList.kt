package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kolos.network.CoinData
import com.example.kolos.ui.theme.KolosTheme
import com.example.kolos.viewmodels.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurrencyCardList(
    navController: NavController,
    coinData: List<CoinData>,
    viewModel: MainViewModel = hiltViewModel()
) {

    var isRefreshing by remember { mutableStateOf(false) }
    var isLoading = viewModel.isLoading.collectAsState()

    var cardModifier = remember {
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .height(70.dp)
    }
    var imageModifier = remember {
        Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp)
    }

    LaunchedEffect(isLoading.value) {
        if (!isLoading.value) {
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
            items(
                items = coinData,
                key = { it.id },
                contentType = { "card" }

            ) { coinData ->
                CurrencyCard(navController, coinData, cardModifier, imageModifier)
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