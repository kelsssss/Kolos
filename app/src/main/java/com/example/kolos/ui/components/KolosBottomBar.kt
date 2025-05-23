package com.example.kolos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kolos.navigation.NavRoute


@Composable
fun KolosBottomBar(navController: NavController) {
    var currentRoute1 by remember { mutableStateOf(navController.currentBackStackEntry?.destination?.route) }

    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                IconButton(
                    onClick = { navController.navigate(NavRoute.MAIN.route) },
                ) {
                    when (currentRoute1) {
                        "main" -> Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        else -> Icon(
                            imageVector = Icons.Outlined.Home,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(40.dp))

                IconButton(
                    onClick = { navController.navigate(NavRoute.FAVOURITE.route) }
                ) {
                    when (currentRoute1) {
                        "favourite" -> Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        else -> Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(40.dp))

                IconButton(
                    onClick = { navController.navigate(NavRoute.ACCOUNT.route) }
                ) {
                    when (currentRoute1) {
                        "account" -> Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )

                        else -> Icon(
                            imageVector = Icons.Outlined.AccountCircle,
                            contentDescription = null,
                            modifier = Modifier.size(35.dp)
                        )
                    }

                }
            }
        }
    )
}