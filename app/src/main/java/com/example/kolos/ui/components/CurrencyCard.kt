package com.example.kolos.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kolos.network.CoinsData
import com.example.kolos.ui.theme.KolosTheme

@Composable
fun CurrencyCard(navController: NavController, coinData: CoinsData){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .height(70.dp)
            .clickable(onClick = {navController.navigate("details")})
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {

            Text(
                text = coinData.name,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 20.dp))
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
fun CurrencyCardPreview(){
    KolosTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
//            CurrencyCard()
        }
    }
}

// https://api.coingecko.com/api/v3/coins/markets(передать в параметрах "usd")
//root - https://api.coingecko.com/api/v3/
//нужный(получить список монет с инфой всей) - https://api.coingecko.com/api/v3/coins/markets?x_cg_demo_api_key=CG-JgzNetfueE5S2carhKRYksD8