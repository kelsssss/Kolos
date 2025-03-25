package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
//import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.database.FavouriteCoinViewModel
import com.example.kolos.network.CoinData
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.net.URLEncoder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(text: String, navController: NavController, coinsData: List<CoinData>? = null, favouriteCoinViewModel: FavouriteCoinViewModel = viewModel(), coinData: CoinData? = null) {

    var currentBackStack by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    var filteredCoins = coinsData?.filter {
        it.name.contains(searchText, ignoreCase = true)
    } ?: emptyList()

    Box {
        TopAppBar(
            title = {
                if (currentBackStack == "main") {
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
                            placeholder = { Text("Поиск монеты...") },
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
                                    text = { Text("Ничего не найдено") },
                                    onClick = { expanded = false }
                                )
                            } else {
                                filteredCoins.take(5).forEach { coin ->
                                    DropdownMenuItem(
                                        text = { Text(coin.name) },
                                        onClick = {
                                            val gson = Gson()
                                            val coinDataJson = gson.toJson(coin)
                                            val encodedCoinDataJson = URLEncoder.encode(coinDataJson, "UTF-8")
                                            expanded = false
                                            navController.navigate("details/$encodedCoinDataJson")
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
                if (currentBackStack == "details/{coinDataJson}") {
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
                if (currentBackStack == "details/{coinDataJson}") {
                    IconButton(
                        onClick = {
                            favouriteCoinViewModel.viewModelScope.launch {
                                favouriteCoinViewModel.insertCoin(
                                    toFavouriteCoin(coinData!!)
                                )
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = null
                        )
                    }
                }
            }
        )
    }
}

