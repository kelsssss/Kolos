package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kolos.network.CoinData
import com.example.kolos.ui.theme.KolosTheme

@Composable
fun CurrencyCardList(navController: NavController, coinData: List<CoinData>) {
    LazyColumn {
        items(coinData) { coinData ->
            CurrencyCard(navController, coinData)
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