package com.example.kolos.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.kolos.ui.components.CurrencyCardList
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.ui.theme.KolosTheme

@Composable
fun MainScreen(navController: NavController){
    Scaffold(
        topBar = { MainTopBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
            CurrencyCardList(modifier = Modifier, navController)
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