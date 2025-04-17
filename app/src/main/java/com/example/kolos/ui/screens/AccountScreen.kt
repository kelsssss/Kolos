package com.example.kolos.ui.screens

import android.app.Activity
import android.content.Context.MODE_PRIVATE
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.navigation.NavRoute
import com.example.kolos.functions.restartApp
import com.example.kolos.ui.components.AlertDialogOnChangePassword
import com.example.kolos.ui.components.AlertDialogOnDeleteUser
import com.example.kolos.ui.components.KolosBottomBar
import com.example.kolos.ui.components.MainTopBar
import com.example.kolos.viewmodels.FavouriteCoinViewModel
import com.example.kolos.viewmodels.FirebaseAuthViewModel
import com.yariksoffice.lingver.Lingver

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

    var context = LocalContext.current
    var activity = context as Activity
//    var forceUpdate = remember {mutableStateOf(false)}




//key(forceUpdate) {
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

            Button(
                onClick = {
                    var prefs = context.getSharedPreferences("prefs", MODE_PRIVATE)
                    var oldLang = prefs.getString("lang", "en") ?: "en"
                    var newLang = when (oldLang) {
                        "en" -> "ru"
                        "ru" -> "en"
                        else -> "en"
                    }

                    prefs.edit().putString("lang", newLang).apply()
                    Lingver.getInstance().setLocale(context, newLang)
//                    context.recreate()
//                    forceUpdate.value = !forceUpdate.value
                    restartApp(activity)

                }
            ) {
                Text(text = stringResource(R.string.change_language))
            }

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
                            navController.navigate(NavRoute.SIGNIN.route)
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
//}
}