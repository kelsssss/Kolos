package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.kolos.ui.theme.KolosTheme

@Composable
fun CurrencyCardList(modifier: Modifier){
    LazyColumn {
        items(15){
            CurrencyCard()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyCardListPreview(){
    KolosTheme {
        Column(
            modifier = Modifier.fillMaxSize()
        ){
            CurrencyCardList(modifier = Modifier)
        }
    }
}