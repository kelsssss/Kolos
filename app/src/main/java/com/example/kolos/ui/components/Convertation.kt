package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
//import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.kolos.network.CoinData
import com.example.kolos.network.Image


@Composable
fun Convertation(coinData: CoinData){
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var inputInFiat by remember { mutableStateOf("") }
        var inputInCrypto by remember { mutableStateOf("") }
        Text(text = "Convertation")

        TextField(
            value = inputInFiat,
            onValueChange = { inputInFiat = it},
            label = { Text(text = "USD")},
            placeholder = { Text (text = "Enter usd amount")}
        )
        TextField(
            value = inputInCrypto,
            onValueChange = {inputInCrypto = it},
            label = { Text(text = coinData.name)},
            placeholder = { Text (text = "Enter ${coinData.name} amount")},
            leadingIcon = {
                AsyncImage(
                    model = coinData.image,
                    contentDescription = null
                    )
            }
        )

        if(inputInFiat != "" && inputInFiat == ""){
            inputInCrypto = (inputInFiat.toDouble() / coinData.price).toString()
        } else if (inputInFiat == "" && inputInFiat != "") {
            inputInFiat = (inputInCrypto.toDouble() * coinData.price).toString()
        }

    }
}