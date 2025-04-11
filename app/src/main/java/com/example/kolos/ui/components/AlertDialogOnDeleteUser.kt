package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.navigation.NavRoute
import com.example.kolos.viewmodels.FirebaseAuthViewModel


@Composable
fun AlertDialogOnDeleteUser(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    navController: NavController,
    authViewModel: FirebaseAuthViewModel,
//    onSuccess: (String, String) -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = stringResource(R.string.enter_your_data_to_confirm_deletion)) },
        text = {
            Column {
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = stringResource(R.string.email)) }
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = stringResource(R.string.password)) }
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    authViewModel.deleteUser(
                        auth = authViewModel.auth,
                        email = email,
                        password = password
                    )
                    navController.navigate(NavRoute.SIGNIN.route)
                    onDismiss()
                }
            ) {
                Text(text = stringResource(R.string.delete_button))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ) {
                Text(text = stringResource(R.string.cancel_button))
            }
        },
    )
}
