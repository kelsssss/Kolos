package com.example.kolos.ui.screens


import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.ui.theme.KolosTheme
import com.example.kolos.viewmodels.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(navController: NavController, authViewModel: FirebaseAuthViewModel = viewModel()){
    var context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}
    var auth = authViewModel.auth
    var passwordIsVisible by remember{mutableStateOf(false)}
    Log.d("MyLog", "User email: ${auth.currentUser?.email}")

//    if(auth.currentUser != null){
//        navController.navigate("main")
//    }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Log In", fontSize = 40.sp)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = "Email") },
                placeholder = { Text(text = "example@mail.com") },
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = "Password") },
                placeholder = { Text(text = "password") },
                visualTransformation =
                    if(passwordIsVisible == false) {
                        PasswordVisualTransformation()
                    } else {
                        VisualTransformation.None
                    },
                trailingIcon = {
                    IconButton(
                        onClick = {
                            passwordIsVisible = !passwordIsVisible
                        }
                    ) {
                        Icon(
                            imageVector = if(passwordIsVisible) Icons.Filled.VisibilityOff
                            else Icons.Filled.Visibility,
                            contentDescription = null
                                )
                    }
                }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    authViewModel.signIn(
                        email = email,
                        password = password,
                        auth = auth,
                        navController = navController,
                        context = context
                    )
                }
            ) {
                Text(text = "Log In")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Don't have an account?")


            Button(
                onClick = {
                    navController.navigate("signUp")
                }
            ) {
                Text(text = "Create account")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview(){
    KolosTheme {
//        SignInScreen()
    }
}



