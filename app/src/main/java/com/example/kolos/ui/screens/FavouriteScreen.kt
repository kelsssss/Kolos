package com.example.kolos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.database.FavouriteCoinViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.ui.components.CurrencyCard
import com.example.kolos.ui.components.FavouriteCurrencyCard
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar

@Composable
fun FavouriteScreen(viewModel: FavouriteCoinViewModel = viewModel(), navController: NavController){

    val coins = viewModel.allCoins.collectAsState(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar("Favourite") },
        bottomBar = { KolosBottomBar(navController) }
    ) { innerPadding->
        Column(modifier = Modifier.padding(innerPadding)) {
            coins.value.forEach {coin ->
                FavouriteCurrencyCard(
                    navController = navController,
                    favouriteCoin = coin
                )
            }
        }
    }




}