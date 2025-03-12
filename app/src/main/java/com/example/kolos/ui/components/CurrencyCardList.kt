package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kolos.network.CoinsData
import com.example.kolos.ui.theme.KolosTheme
import androidx.compose.foundation.lazy.items

@Composable
fun CurrencyCardList(modifier: Modifier, navController: NavController, coinsData: List<CoinsData>){
    LazyColumn {
        items(coinsData){coinData->
            CurrencyCard(navController, coinData)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyCardListPreview(){
    KolosTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
//            CurrencyCardList(modifier = Modifier)
        }
    }
}