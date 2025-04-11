package com.example.kolos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kolos.navigation.KolosNavigation
import com.example.kolos.navigation.NavRoute
import com.example.kolos.ui.theme.KolosTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    //    val mainViewModel = hiltViewModel<MainViewModel>()
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        auth = Firebase.auth
        enableEdgeToEdge()
        setContent {
            KolosTheme {
                if (auth.currentUser != null) {
                    KolosNavigation(startDestination = NavRoute.MAIN.route)
                } else {
                    KolosNavigation(startDestination = NavRoute.SIGNIN.route)
                }

            }
        }
    }
}


