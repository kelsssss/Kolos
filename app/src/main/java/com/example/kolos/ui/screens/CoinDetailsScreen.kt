package com.example.kolos.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.kolos.R
import com.example.kolos.network.CoinData
import com.example.kolos.ui.components.Chart
import com.example.kolos.ui.components.MainTopBar

@Composable
fun CoinDetailsScreen(navController: NavController, coinData: CoinData){
    Scaffold(
        topBar = { MainTopBar() },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ){
//            coinData.name
            Text(text = "Описание Монеты(тест):")
            Text(text = "Название: ${coinData.name}")
            Text(text = coinData.symbol)

            SubcomposeAsyncImage(
                model = coinData.image,
                contentDescription = null,
                modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp),
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
            Text(text = "График:")
            Chart(coinData.sparkline.price)
        }
    }
}