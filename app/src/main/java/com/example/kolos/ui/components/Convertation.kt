package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.kolos.R
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
        Text(text = stringResource(R.string.convertation))

        TextField(
            value = inputInFiat,
            onValueChange = { inputInFiat = it},
            label = { Text(text = stringResource(R.string.usd))},
            placeholder = { Text (text = stringResource(R.string.enter_usd_amount))}
        )
        TextField(
            value = inputInCrypto,
            onValueChange = {inputInCrypto = it},
            label = { Text(text = coinData.name)},
            placeholder = { Text (text = stringResource(R.string.enter_amount, coinData.name))},
            leadingIcon = {
                AsyncImage(
                    model = coinData.image,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                    )
            }
        )

        if(inputInFiat != "" && inputInCrypto == ""){
            inputInCrypto = (inputInFiat.toDouble() / coinData.price).toString()
        } else if (inputInFiat == "" && inputInCrypto != "") {
            inputInFiat = (inputInCrypto.toDouble() * coinData.price).toString()
        }

    }
}