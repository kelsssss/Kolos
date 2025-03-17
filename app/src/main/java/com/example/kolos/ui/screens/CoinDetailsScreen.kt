package com.example.kolos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
//import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.kolos.R
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.database.FavouriteCoinViewModel
import com.example.kolos.network.CoinData
import com.example.kolos.ui.components.Chart
import com.example.kolos.ui.components.MainTopBar
import kotlinx.coroutines.launch

@Composable
fun CoinDetailsScreen(
    navController: NavController,
    coinData: CoinData,
    viewModel: FavouriteCoinViewModel = viewModel()
) {

    Scaffold(
        topBar = { MainTopBar("Details", navController = navController, coinData = coinData ) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                SubcomposeAsyncImage(
                    model = coinData.image,
                    contentDescription = null,
                    modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp, end = 10.dp),
                    loading = {
                        CircularProgressIndicator(
                            modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp, end = 10.dp),
                            color = Color.White
                        )
                    },
                    error = {
                        Image(
                            painter = painterResource(R.drawable.error),
                            contentDescription = null,
                            modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp)
                        )
                    }
                )
                Column {
                    Text(text = coinData.name, fontSize = 50.sp)
                    Text(text = coinData.symbol, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
            }

            Text(text = "Цена: ${coinData.price}$", fontSize = 30.sp, modifier = Modifier.padding(bottom = 20.dp))


//            Button(
//                onClick = {
//                    viewModel.viewModelScope.launch {
//                        viewModel.insertCoin(
//                            FavouriteCoin(
//                                id = coinData.id,
//                                image = coinData.image,
//                                symbol = coinData.symbol,
//                                name = coinData.name
//                            )
//                        )
//                    }
//                }
//            ) {
//                Icon(Icons.Default.Favorite, contentDescription = null)
//            }
            Row {
                Spacer(modifier = Modifier.weight(1f))
//                Text(
//                    text = "График цены:",
//                    fontSize = 30.sp,
//                    textAlign = TextAlign.Center,
//                    modifier = Modifier.padding(vertical = 15.dp)
//                )
                Spacer(modifier = Modifier.weight(1f))

            }

            Chart(coinData.sparkline.price)

        }
    }
}