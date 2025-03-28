package com.example.kolos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.kolos.functions.encodedJsonToCoin
import com.example.kolos.network.CoinData
import com.example.kolos.ui.screens.AccountScreen
import com.example.kolos.ui.screens.CoinDetailsScreen
import com.example.kolos.ui.screens.FavouriteCoinScreen
import com.example.kolos.ui.screens.FavouriteScreen
import com.example.kolos.ui.screens.MainScreen
import com.example.kolos.ui.screens.SignInScreen
import com.example.kolos.ui.screens.SignUpScreen
import com.google.gson.Gson
import java.net.URLDecoder

@Composable
fun KolosNavigation(
    startDestination: String
) {
    var navController = rememberNavController()

    NavHost(
        navController = navController,
//        startDestination = "main"
        startDestination = startDestination
    ) {
        composable(
            route = "main",
        ) {
            MainScreen(navController = navController)
        }
        composable(
            route = "details/{coinDataJson}",
            arguments = listOf(
                navArgument("coinDataJson") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val encodedCoinDataJson = backStackEntry.arguments?.getString("coinDataJson")
            val coinData = encodedJsonToCoin(encodedCoinDataJson)

            CoinDetailsScreen(
                navController = navController,
                coinData = coinData
            )
        }

        composable(
            route = "favourite",
            ) {
            FavouriteScreen(navController = navController)
        }

        composable(
            route = "favouriteCoin/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            FavouriteCoinScreen(navController = navController, coinId = id!!)
        }

        composable(
            route = "signUp",
        ) {
            SignUpScreen(navController = navController)
        }

        composable(
            route = "signIn",
        ) {
            SignInScreen(navController = navController)
        }

        composable(
            route = "account",
        ) {
            AccountScreen(navController = navController)
        }
    }
}