package com.example.kolos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.MainViewModel
import com.example.kolos.ui.components.CurrencyCardList
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.ui.theme.KolosTheme
import androidx.compose.runtime.getValue

@Composable
fun MainScreen(viewModel: MainViewModel = viewModel(), navController: NavController){
    val coinsData by viewModel.coinsData.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchCoinsData()
    }

    Scaffold(
        topBar = { MainTopBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
//            if (coinsData != null){
//                Text("Загружено!")
//            }
            coinsData?.let { CurrencyCardList(modifier = Modifier, navController, it) }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview(){
    KolosTheme(darkTheme = true) {
//        MainScreen()
    }
}