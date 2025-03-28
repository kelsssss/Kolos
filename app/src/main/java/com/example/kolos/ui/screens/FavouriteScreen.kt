package com.example.kolos.ui.screens

import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.ui.components.FavouriteCurrencyCard
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar

@Composable
fun FavouriteScreen(
    viewModel: FavouriteCoinViewModel = viewModel(),
    navController: NavController
) {

    val coins = viewModel.allCoins.collectAsState(emptyList())

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar("Favourite", navController) },
        bottomBar = { KolosBottomBar(navController) }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())) {
            coins.value.forEach { coin ->
                FavouriteCurrencyCard(
                    navController = navController,
                    favouriteCoin = coin
                )
            }
        }
    }


}