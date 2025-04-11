package com.example.kolos.ui.screens

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.ui.components.AlertDialogOnChangePassword
import com.example.kolos.ui.components.AlertDialogOnDeleteUser
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.example.kolos.viewmodels.FirebaseAuthViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountScreen(
    navController: NavController,
//    authViewModel: FirebaseAuthViewModel = viewModel(),
//    favouriteCoinViewModel: FavouriteCoinViewModel = viewModel(),
    authViewModel: FirebaseAuthViewModel = hiltViewModel(),
    favouriteCoinViewModel: FavouriteCoinViewModel = hiltViewModel()
) {

    var showDialog by remember { mutableStateOf(false) }
    var showDialogToExit by remember { mutableStateOf(false) }
    var showDialogToChangePassword by remember { mutableStateOf(false) }
    var auth = authViewModel.auth

    Scaffold(
        topBar = {
            MainTopBar(
                text = stringResource(R.string.account),
                navController = navController
            )
        },
        bottomBar = {
            KolosBottomBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
//            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.your_email))

            Text(text = "${auth.currentUser?.email}")

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.log_out))
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = {
                    showDialogToExit = true
//                    authViewModel.signOut(auth)
//                    favouriteCoinViewModel.deleteAllCoins()
//                    navController.navigate("signIn")
                }
            ) {
                Text(text = stringResource(R.string.log_out_button))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.change_password))
            Spacer(modifier = Modifier.height(10.dp))


            Button(
                onClick = {
                    showDialogToChangePassword = true
                }
            ) {
                Text(text = stringResource(R.string.change_password_button))
            }

            Spacer(modifier = Modifier.height(20.dp))


            Text(text = stringResource(R.string.delete_account))
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    showDialog = true
                },

                colors = ButtonColors(
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
                    disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor
                )
            ) {
                Text(text = stringResource(R.string.delete_button))
            }


        }
        if (showDialog) {
            AlertDialogOnDeleteUser(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                authViewModel = authViewModel,
                navController = navController,
            )
        }

        if (showDialogToExit) {
            AlertDialog(
                onDismissRequest = { showDialogToExit = false },
                title = { Text(text = stringResource(R.string.log_out_confirmation_text)) },
                dismissButton = {
                    Button(
                        onClick = {
                            showDialogToExit = false
                        }
                    ) {
                        Text(text = stringResource(R.string.cancel_button))
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            authViewModel.signOut(auth)
                            favouriteCoinViewModel.deleteAllCoins()
                            showDialogToExit = false
                            navController.navigate("signIn")
                        }
                    ) {
                        Text(text = stringResource(R.string.i_am_sure_button))
                    }
                }

            )
        }
        if (showDialogToChangePassword) {
            AlertDialogOnChangePassword(
                showDialog = showDialogToChangePassword,
                onDismiss = { showDialogToChangePassword = false },
                navController = navController,
                authViewModel = authViewModel
            )
        }
    }
}