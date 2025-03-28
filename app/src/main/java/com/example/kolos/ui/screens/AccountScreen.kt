package com.example.kolos.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.ui.components.AlertDialogOnDeleteUser
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.viewmodels.FirebaseAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(navController: NavController, authViewModel: FirebaseAuthViewModel = viewModel()){
    var showDialog by remember { mutableStateOf(false) }
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
                    showDialog = true
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
        if(showDialog){
            AlertDialogOnDeleteUser(
                showDialog = showDialog,
                onDismiss = { showDialog = false},
                authViewModel = authViewModel,
                navController = navController
            )
        }


    }
}