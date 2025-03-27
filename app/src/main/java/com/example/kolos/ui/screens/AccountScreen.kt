package com.example.kolos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.viewmodels.FirebaseAuthViewModel

@Composable
fun AccountScreen(navController: NavController, authViewModel: FirebaseAuthViewModel = viewModel()){
    var auth = authViewModel.auth
    Scaffold(
        topBar = {
            MainTopBar(
            text = "Account",
            navController = navController
            )
        },
        bottomBar = {
            KolosBottomBar(
                navController = navController
            )
        }
    ) { innerPadding->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Ваша почта:")

            Text(text = "${auth.currentUser?.email}")

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Выйти из аккаунта:")
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = {
                    authViewModel.signOut(auth)
                    navController.navigate("signIn")
                }
            ) {
                Text(text = "Выйти")
            }
            Spacer(modifier = Modifier.height(20.dp))


            Text(text = "Удалить аккаунт:")
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    //TODO: СДЕЛАТЬ ДИАЛОГ ДЛЯ ПОДТВЕРЖДЕНИЯ УДАЛЕНИЯ, ТУТ ПРОСТО ПРОВЕРКА
                    authViewModel.deleteUser(
                        auth = auth,
                        email = auth.currentUser?.email ?:"",
                        password = "123456789"
                    )
                    navController.navigate("signIn")
                },
                colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor)
            ) {
                Text(text = "Удалить")
            }


        }

    }
}