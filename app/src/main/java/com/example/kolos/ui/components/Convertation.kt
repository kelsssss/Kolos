package com.example.kolos.ui.components

//import androidx.compose.material3.TextField
//import com.example.kolos.network.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cached
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kolos.R
import com.example.kolos.network.CoinData


@Composable
fun Convertation(coinData: CoinData) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var inputInFiat by remember { mutableStateOf("") }
        var inputInCrypto by remember { mutableStateOf("") }

        Text(
            text = stringResource(R.string.convertation),
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        TextField(
            value = inputInFiat,
            onValueChange = { inputInFiat = it },
            label = { Text(text = stringResource(R.string.usd)) },
            placeholder = { Text(text = stringResource(R.string.enter_usd_amount)) },
            leadingIcon = {
                Image(
                    painter = painterResource(R.drawable.dollar),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        inputInCrypto = (inputInFiat.toDouble() / coinData.price).toString()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Cached,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    inputInCrypto = (inputInFiat.toDouble() / coinData.price).toString()
                }
            ),
            modifier = Modifier.padding(bottom = 5.dp)
        )
        TextField(
            value = inputInCrypto,
            onValueChange = { inputInCrypto = it },
            label = { Text(text = coinData.name) },
            placeholder = { Text(text = stringResource(R.string.enter_amount, coinData.name)) },
            leadingIcon = {
                AsyncImage(
                    model = coinData.image,
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        inputInFiat = (inputInCrypto.toDouble() * coinData.price).toString()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Cached,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    inputInFiat = (inputInCrypto.toDouble() * coinData.price).toString()
                }
            )
        )

//        if(inputInFiat != "" && inputInCrypto == ""){
//            inputInCrypto = (inputInFiat.toDouble() / coinData.price).toString()
//        } else if (inputInFiat == "" && inputInCrypto != "") {
//            inputInFiat = (inputInCrypto.toDouble() * coinData.price).toString()
//        }

    }
}