package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Favorite
import androidx.compose.material.icons.sharp.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun KolosBottomBar(navController: NavController, currentRoute: String?) {
//    var currentBackStack by remember{ mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { navController.navigate("main") },
                    ) {
                    when(currentRoute){
                        "main" -> Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                        else -> Icon(
                            imageVector = Icons.Sharp.Home,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(40.dp))

                IconButton(
                    onClick = { navController.navigate("favourite") }
                ) {
                    when(currentRoute){
                        "favourite" -> Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                        else -> Icon(
                            imageVector = Icons.Sharp.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                }
            }
        }
    )
}