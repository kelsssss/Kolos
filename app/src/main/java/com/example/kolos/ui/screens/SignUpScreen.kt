package com.example.kolos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kolos.ui.theme.KolosTheme

@Composable
fun SignUpScreen(){
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        Box(contentAlignment = Alignment.Center){

        Text(text = "Sign Up", fontSize = 40.sp)
        Spacer(modifier = Modifier.height(20.dp))
            OutlinedTextField(
                value = email,
                onValueChange = {email = it},
                label = {Text(text = "Email")},
                placeholder = {Text(text = "example@mail.com")},
            )

            Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = {password = it},
            label = {Text(text = "Password")},
            placeholder = {Text(text = "password")}
        )
        Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {}
            ) {
                Text(text = "Sign Up")
            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    KolosTheme {
        SignUpScreen()
    }
}