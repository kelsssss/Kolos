package com.example.kolos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.kolos.R
import com.example.kolos.functions.coinDataToEncodedJson
import com.example.kolos.network.CoinData
import com.example.kolos.ui.theme.KolosTheme
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun CurrencyCard(
    navController: NavController,
    coinData: CoinData,
    cardModifier: Modifier,
    imageModifier: Modifier
) {

    val encodedCoinDataJson = coinDataToEncodedJson(coinData)

    var clickableModifier = remember(cardModifier) {
        cardModifier.clickable(onClick = { navController.navigate("details/$encodedCoinDataJson") })
    }



    Card(
        modifier = clickableModifier
    ) {
//            Modifier
//            .fillMaxWidth()
//            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
//            .height(70.dp)

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
//            AsyncImage(
                model = coinData.image,
                contentDescription = null,
                modifier = imageModifier
                    .size(50.dp),
                loading = {
                    CircularProgressIndicator(
                        modifier = imageModifier,
                        color = Color.White
                    )
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.error),
                        contentDescription = null,
                        modifier = imageModifier
                    )
                }
            )


            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = coinData.name,
                    fontSize = 20.sp,
                )
                Text(
                    text = coinData.symbol,
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
            Text(
                text = "${coinData.price}$",
                fontSize = 25.sp,
                modifier = Modifier
                    .padding(end = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyCardPreview() {
    KolosTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
//            CurrencyCard()
        }
    }
}