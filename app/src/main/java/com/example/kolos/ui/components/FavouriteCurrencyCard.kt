package com.example.kolos.ui.components


//import androidx.navigation.NavController
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import com.example.kolos.R
import com.example.kolos.database.FavouriteCoin
import com.example.kolos.navigation.NavRoute
import com.example.kolos.viewmodels.FavouriteCoinViewModel

@Composable
fun FavouriteCurrencyCard(
    navController: NavController,
    favouriteCoin: FavouriteCoin,
    viewModel: FavouriteCoinViewModel = hiltViewModel()
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .height(70.dp)
            .clickable(onClick = { navController.navigate("${NavRoute.FAVOURITECOIN.route}/${favouriteCoin.id}") })
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            SubcomposeAsyncImage(
                model = favouriteCoin.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 10.dp, top = 7.dp, bottom = 7.dp)
                    .size(50.dp),
                loading = {
                    CircularProgressIndicator(
                        modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp),
                        color = Color.White
                    )
                },
                error = {
                    Image(
                        painter = painterResource(R.drawable.error),
                        contentDescription = null,
                        modifier = Modifier.padding(start = 10.dp, top = 7.dp, bottom = 7.dp)
                    )
                }
            )


            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
            ) {
                Text(
                    text = favouriteCoin.name,
                    fontSize = 20.sp,
                )
                Text(
                    text = favouriteCoin.symbol,
                    fontSize = 15.sp,
                    color = Color.LightGray
                )
            }

            Spacer(
                modifier = Modifier
                    .weight(1f)
            )
//            IconButton(
//                onClick = {
//                    viewModel.viewModelScope.launch {
//                        viewModel.deleteCoin(favouriteCoin.id)
//                    }
//                }
//            ) {
//                Icon(
//                    imageVector = Icons.Default.Delete,
//                    contentDescription = null
//                )
//            }
        }
    }
}