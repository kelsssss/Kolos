package com.example.kolos.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CurrencyCard(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row {
            Text(text = "TestName")
            Spacer(
                modifier = Modifier.fillMaxWidth()
            )
            Text(text = "1.08$")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun