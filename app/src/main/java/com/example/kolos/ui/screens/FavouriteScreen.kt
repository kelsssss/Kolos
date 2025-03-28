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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.ui.components.FavouriteCurrencyCard
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//rules_version = '2';
//
//service cloud.firestore {
//    match /databases/{database}/documents {
//        match /{document=**} {
//            allow read, write: if true;
//        }
//    }
//}

@Composable
fun FavouriteScreen(
    viewModel: FavouriteCoinViewModel = viewModel(),
    navController: NavController
) {
    val coins = viewModel.allCoins.collectAsState(emptyList())
    val fs = Firebase.firestore
    var listOfCoinsByFs: List<FavouriteCoin> by remember{ mutableStateOf(emptyList<FavouriteCoin>()) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MainTopBar("Favourite", navController) },
        bottomBar = { KolosBottomBar(navController) }
    ) { innerPadding ->

        LaunchedEffect(Unit) {
            //TODO: для фс сделать проверку на дубликаты минимальную
            fs.collection("test").get().addOnCompleteListener { task ->
                if(task.isSuccessful){
                    listOfCoinsByFs = task.result.toObjects(FavouriteCoin::class.java)
                }
            }.addOnSuccessListener {
                listOfCoinsByFs.forEach { coinFS->
                    viewModel.insertCoin(coinFS)
                }
                coins.value.forEach { coinDB->
                    fs.collection("test").document(coinDB.id).set(coinDB)
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