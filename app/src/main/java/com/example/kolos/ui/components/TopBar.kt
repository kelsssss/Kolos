package com.example.kolos.ui.components

//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.runtime.remember
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.functions.coinDataToEncodedJson
import com.example.kolos.functions.coinToFavouriteCoin
import com.example.kolos.navigation.NavRoute
import com.example.kolos.network.CoinData
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    text: String,
    navController: NavController,
    coinsData: List<CoinData>? = null,
    favouriteCoinViewModel: FavouriteCoinViewModel = hiltViewModel(),
    coinData: CoinData? = null,

    isCloseButtonNeeded: Boolean = false,
    isSearchNeeded: Boolean = false,
    isFavouriteButtonNeeded: Boolean = false,
) {

//    var currentBackStack by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var fs = Firebase.firestore
    var uid = Firebase.auth.currentUser?.uid ?: run {
        navController.navigate(NavRoute.SIGNIN.route)
        Log.w("MyLog", "User not authenticated")
        return
    }

    var filteredCoins = coinsData?.filter {
        it.name.contains(searchText, ignoreCase = true)
    } ?: emptyList()

//    var isThisCoinFavourite: List<Pair<String, Boolean>> by remember { mutableStateOf(emptyList<Pair<String, Boolean>>()) }

    var isThisCoinFavourite by remember { mutableStateOf(false) }
    if (coinData != null) {
        LaunchedEffect(Unit) {
            if (favouriteCoinViewModel.isCoinFavourite(coinData.name) != 0) {
                isThisCoinFavourite = true
            }
        }
    }



    Box {
        TopAppBar(
            title = {
//                if (currentBackStack == "main") {
                if (isSearchNeeded) {
                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = { expanded = it }
                    ) {
                        OutlinedTextField(
                            value = searchText,
                            onValueChange = {
                                searchText = it
                                expanded = it.isNotEmpty()
                            },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryEditable)
                                .fillMaxWidth(),
                            placeholder = { Text(stringResource(R.string.search_coin)) },
                            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            },
                            colors = ExposedDropdownMenuDefaults.textFieldColors()
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            if (filteredCoins.isEmpty()) {
                                DropdownMenuItem(
                                    text = { Text(stringResource(R.string.nothing_found)) },
                                    onClick = { expanded = false }
                                )
                            } else {
                                filteredCoins.take(5).forEach { coin ->
                                    DropdownMenuItem(
                                        text = { Text(coin.name) },
                                        onClick = {
                                            var encodedCoinDataJson = coinDataToEncodedJson(coin)
                                            expanded = false
                                            navController.navigate("${NavRoute.DETAILS.route}/$encodedCoinDataJson")
//                                            navController.navigate("details/$encodedCoinDataJson")
                                        }
                                    )
                                }
                            }
                        }
                    }
                } else {
                    Text(
                        text = text,
                        fontSize = 30.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            },
            navigationIcon = {
//                if (currentBackStack == "details/{coinDataJson}") {
                if (isCloseButtonNeeded) {
                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {
                        Icon(
                            imageVector = Icons.Sharp.Close,
                            contentDescription = null
                        )
                    }
                }
            },
            actions = {

//                if (currentBackStack == "details/{coinDataJson}") {
                if (isFavouriteButtonNeeded) {
                    IconButton(
                        onClick = {
//                            isThisCoinFavourite += Pair(coinData!!.name, true)
                            when (isThisCoinFavourite) {
                                false -> {
                                    favouriteCoinViewModel.viewModelScope.launch {
                                        favouriteCoinViewModel.insertCoin(
                                            coinToFavouriteCoin(
                                                coinData!!
                                            )
                                        )
                                    }
                                }

                                true -> {
                                    favouriteCoinViewModel.viewModelScope.launch {
                                        favouriteCoinViewModel.deleteCoin(coinData!!.id)
                                    }
//                                    fs.collection("test").document(coinData!!.id).delete()
                                    fs.collection("test").document(uid).collection("coins")
                                        .document(coinData!!.id)
                                }
                            }

                            isThisCoinFavourite = !isThisCoinFavourite
                        }
                    ) {
                        when (isThisCoinFavourite) {
                            true -> Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null
                            )

                            false -> Icon(
                                imageVector = Icons.Outlined.FavoriteBorder,
                                contentDescription = null
                            )
                        }

                    }
                }
            }
        )
    }
}

