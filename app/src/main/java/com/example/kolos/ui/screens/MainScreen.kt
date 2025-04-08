package com.example.kolos.ui.screens

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.viewmodels.MainViewModel
import com.example.kolos.ui.components.CurrencyCardList
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.ui.theme.KolosTheme
import kotlin.getValue
//import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    navController: NavController
) {
//    val viewModel: MainViewModel = hiltViewModel()
    val coinsData by viewModel.coinData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCoinsData()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar("Coins", navController, coinsData = coinsData, isSearchNeeded = true) },
        bottomBar = { KolosBottomBar(navController) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            when(coinsData == null){
                true -> CircularProgressIndicator(color = Color.White)
                false -> coinsData?.let { CurrencyCardList(navController, it) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    KolosTheme(darkTheme = true) {
//        MainScreen()
    }
}