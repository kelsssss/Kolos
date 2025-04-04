package com.example.kolos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kolos.ui.components.KolosNavigation
import com.example.kolos.ui.theme.KolosTheme
import com.example.kolos.viewmodels.MainViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        enableEdgeToEdge()
        setContent {
            KolosTheme {
                if(auth.currentUser != null){
                    KolosNavigation(startDestination = "main")
                } else{
                    KolosNavigation(startDestination = "signIn")
                }

            }
        }
    }
}


