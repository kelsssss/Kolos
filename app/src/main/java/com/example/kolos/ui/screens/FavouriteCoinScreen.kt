package com.example.kolos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.kolos.viewmodels.FavouriteCoinDetailsViewModel
import com.example.kolos.R
import com.example.kolos.ui.components.Chart
import com.example.kolos.ui.components.MainTopBar
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import com.example.kolos.functions.coinDataByIdToCoinData
import com.example.kolos.ui.components.Convertation

@Composable
fun FavouriteCoinScreen(
    navController: NavController,
    coinId: String,
    favouriteCoinDetailsViewModel: FavouriteCoinDetailsViewModel = viewModel()
){

    val fetchedCoinData by favouriteCoinDetailsViewModel.coinData.collectAsState()


    LaunchedEffect(Unit) {
        favouriteCoinDetailsViewModel.getCoinInfoById(coinId)
    }


    Scaffold(
        topBar = {
            fetchedCoinData?.let {
                MainTopBar(
                    text = stringResource(R.string.details_top_bar),
                    navController = navController,
                    isCloseButtonNeeded = true,
                    isFavouriteButtonNeeded = true,
                    coinData = coinDataByIdToCoinData(it)
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(fetchedCoinData == null){
                CircularProgressIndicator(
                    modifier = Modifier.padding(16.dp),
                    color = Color.White
                )
            } else {
                val coinData = fetchedCoinData!!
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(top = 20.dp, bottom = 30.dp)
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    SubcomposeAsyncImage(
                        model = coinData.image.small,
                        contentDescription = null,
                        modifier = Modifier.padding(
                            start = 10.dp,
                            top = 7.dp,
                            bottom = 7.dp,
                            end = 10.dp
                        )
                            .size(80.dp),
                        loading = {
                            CircularProgressIndicator(
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 7.dp,
                                    bottom = 7.dp,
                                    end = 10.dp
                                ),
                                color = Color.White
                            )
                        },
                        error = {
                            Image(
                                painter = painterResource(R.drawable.error),
                                contentDescription = null,
                                modifier = Modifier.padding(
                                    start = 10.dp,
                                    top = 7.dp,
                                    bottom = 7.dp
                                )
                            )
                        }
                    )
                    Column {
                            Text(text = coinData.name, fontSize = 50.sp)
                            Text(text = coinData.symbol, fontSize = 20.sp)


                    }
                    Spacer(modifier = Modifier.weight(1f))
                }

                Text(
                    text = stringResource(R.string.price, coinData.marketData.price.usd),
//                        "Цена: ${coinData.marketData.price.usd}$",
                    fontSize = 30.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )
                Convertation(
                    coinData = coinDataByIdToCoinData(coinData)
                )
                Chart(coinData.marketData.sparkline.price)
            }
        }
    }
}