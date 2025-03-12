package com.example.kolos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.compose.con
import androidx.navigation.compose.rememberNavController
import com.example.kolos.ui.screens.CoinDetailsScreen
import com.example.kolos.ui.screens.MainScreen

@Composable
fun KolosNavigation(){
    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "main"
        ) {
            composable(route = "main"){
                MainScreen(navController = navController)
            }
        composable(route = "details") {
            CoinDetailsScreen(navController = navController)
        }
    }
}