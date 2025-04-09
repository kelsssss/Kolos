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
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.kolos.R
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.ui.theme.KolosTheme
import com.example.kolos.viewmodels.FirebaseAuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@Composable
fun SignInScreen(
    navController: NavController,
//    authViewModel: FirebaseAuthViewModel = viewModel()
    authViewModel: FirebaseAuthViewModel = hiltViewModel()
){
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
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.log_in), fontSize = 40.sp)
            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text(text = stringResource(R.string.email)) },
                placeholder = { Text(text = stringResource(R.string.email_placeholder)) },
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text(text = stringResource(R.string.password)) },
                placeholder = { Text(text = stringResource(R.string.password_placeholder)) },
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
                Text(text = stringResource(R.string.log_in))
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = stringResource(R.string.don_t_have_an_account))


            Button(
                onClick = {
                    navController.navigate("signUp")
                }
            ) {
                Text(text = stringResource(R.string.create_account))
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



