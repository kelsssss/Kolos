package com.example.kolos.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material.icons.sharp.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(text: String, navController: NavController, coinsData: List<CoinData>? = null, favouriteCoinViewModel: FavouriteCoinViewModel = viewModel(), coinData: CoinData? = null) {

    var currentBackStack by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }
    var searchText by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            if(currentBackStack == "main"){
                SearchBar(
                    inputField = {
                        SearchBarDefaults.InputField(
                            expanded = expanded,
                            onQueryChange = {searchText = it},
                            query = searchText,
                            onSearch = {},
                            onExpandedChange = {expanded = !expanded},
                            placeholder = { Text(text = "Поиск монеты...")},
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null
                                )

                            }
                        )
                                 },
                    expanded = expanded,
                    onExpandedChange =  {expanded = !expanded},

                    ) { }
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
            if (currentBackStack == "details/{coinDataJson}"){
                IconButton(
                    onClick = {navController.popBackStack()}
                ) {
                    Icon(
                        imageVector = Icons.Sharp.Close,
                        contentDescription = null
                    )
                }
            }
        },
        actions = {
            if (currentBackStack == "details/{coinDataJson}"){
                IconButton(
                    onClick = {
                        favouriteCoinViewModel.viewModelScope.launch {
                            favouriteCoinViewModel.insertCoin(
//                                FavouriteCoin(
//                                    id = coinData!!.id,
//                                    image = coinData.image,
//                                    name = coinData.name,
//                                    symbol = coinData.symbol
//                                )
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