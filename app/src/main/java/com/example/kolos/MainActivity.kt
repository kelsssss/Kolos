package com.example.kolos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.kolos.ui.components.KolosNavigation
import com.example.kolos.ui.theme.KolosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KolosTheme {
                KolosNavigation()
            }
        }
    }
}


