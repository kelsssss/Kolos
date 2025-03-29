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
import androidx.navigation.NavController
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
        title = {Text(text = "Введите свои данные для подтверждения удаления")},
        text = {
            Column {
                TextField(
                    value = email,
                    onValueChange = {email = it},
                    label = {Text(text = "Email")}
                )
                TextField(
                    value = password,
                    onValueChange = {password = it},
                    label = {Text(text = "Password")}
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
                    navController.navigate("signIn")
                    onDismiss()
                }
            ) {
                Text(text = "Delete")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDismiss()
                }
            ){
                Text(text = "Cancel")
            }
        },
        )
}
