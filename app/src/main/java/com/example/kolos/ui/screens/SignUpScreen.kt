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
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kolos.ui.theme.KolosTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

@Composable
fun SignUpScreen(navController: NavController){
    var context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember {mutableStateOf("")}

    var auth = Firebase.auth


    Scaffold { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(text = "Create account", fontSize = 40.sp)
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
                placeholder = { Text(text = "password") }
            )
            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    signUp(email = email, password = password, auth = auth, navController = navController, context = context)
                }
            ) {
                Text(text = "Create account")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Already have a account?")


            Button(
                onClick = {
                    navController.navigate("signIn")
                }
            ) {
                Text(text = "Log in")
            }
//        }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    KolosTheme {
//        SignUpScreen()
    }
}

private fun signUp(email: String, password: String, auth: FirebaseAuth, navController: NavController, context: Context){
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if(task.isSuccessful){
                Log.d("MyLog", "Sign Up Successful!")
                navController.navigate("main")
            } else {
                Log.d("MyLog", "Sign Up Failed")
                Toast.makeText(
                    context,
                    "Sign Up Failed",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}

