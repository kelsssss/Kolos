package com.example.kolos.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.ui.components.FavouriteCurrencyCard
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun FavouriteScreen(
//    viewModel: FavouriteCoinViewModel = viewModel(),
    viewModel: FavouriteCoinViewModel = hiltViewModel(),
    navController: NavController
) {
    val coins = viewModel.allCoins.collectAsState(emptyList())
    val fs = Firebase.firestore
    var listOfCoinsByFs: List<FavouriteCoin> by remember { mutableStateOf(emptyList<FavouriteCoin>()) }
    var uid = Firebase.auth.currentUser?.uid ?: run {
        navController.navigate("signIn")
        Log.w("MyLog", "User not authenticated")
        return
    }
//    Log.d("MyLog", "Uid is null")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar(stringResource(R.string.favourite), navController) },
        bottomBar = { KolosBottomBar(navController) }
    ) { innerPadding ->

        LaunchedEffect(Unit) {
            //TODO: для фс сделать проверку на дубликаты минимальную
//            fs.collection("test").get().addOnCompleteListener { task ->
            fs.collection("test").document(uid).collection("coins").get()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        listOfCoinsByFs = task.result.toObjects(FavouriteCoin::class.java)
                    }
                }.addOnSuccessListener {
                    listOfCoinsByFs.forEach { coinFS ->
                        viewModel.insertCoin(coinFS)
                    }
                    coins.value.forEach { coinDB ->
                        fs.collection("test").document(uid).collection("coins").document(coinDB.id)
                            .set(coinDB)
                    }
                }
        }
//        Column(modifier = Modifier.padding(innerPadding).verticalScroll(rememberScrollState())) {
//            listOfCoinsByFs.forEach { coin ->
//                FavouriteCurrencyCard(
//                    navController = navController,
//                    favouriteCoin = coin
//                )
//            }
//        }


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            coins.value.forEach { coin ->
                FavouriteCurrencyCard(
                    navController = navController,
                    favouriteCoin = coin
                )
            }
        }
    }
}