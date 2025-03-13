package com.example.kolos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kolos.network.CoinData
import com.example.kolos.ui.screens.CoinDetailsScreen
import com.example.kolos.ui.screens.MainScreen
import com.google.gson.Gson
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun KolosNavigation(){
    var navController = rememberNavController()
    val gson = Gson()

    NavHost(
        navController = navController,
        startDestination = "main"
        ) {
            composable(
                route = "main"
            ){
                MainScreen(navController = navController)
            }
            composable(
                route = "details/{coinDataJson}",
                arguments = listOf(
                    navArgument("coinDataJson"){
                    type = NavType.StringType
                }
                )
            ) { backStackEntry ->
                val encodedCoinDataJson = backStackEntry.arguments?.getString("coinDataJson")
                val coinDataJson = URLDecoder.decode(encodedCoinDataJson, "UTF-8")
                val coinData = gson.fromJson(coinDataJson, CoinData::class.java)
                CoinDetailsScreen(navController = navController, coinData)
            }
        }
}