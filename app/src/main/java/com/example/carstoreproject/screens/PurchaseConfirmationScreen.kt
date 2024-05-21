package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.components.ButtonComponent
import com.example.carstoreproject.data.viewmodels.CarsViewModel
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.models.Car

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PurchaseConfirmationScreen(
    car: Car,
    navController: NavController,
    carsViewModel: CarsViewModel,
    userViewModel: UserViewModel
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium_padding)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(R.string.confirmation),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        Divider(color = MaterialTheme.colorScheme.onBackground)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.medium_padding)),
        ) {
            CarCard(
                car = car,
                navController = navController,
                isFavorite = userViewModel.isFavorite(car.name!!),
                onFavoriteClick = { userViewModel.toggleFavoriteCar(it) }
            )
            Text(
                text = stringResource(R.string.user_details),
                style = MaterialTheme.typography.headlineSmall,)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.small_padding)),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(R.string.full_name),
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 20.sp
                        )
                        Text(
                            text = "${userViewModel.user.value?.firstName!!} ${userViewModel.user.value?.lastName}",
                            style = MaterialTheme.typography.displayMedium,
                            fontSize = 20.sp
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.small_padding)),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(R.string.email),
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 20.sp
                        )
                        Text(
                            text = userViewModel.user.value?.email!!,
                            style = MaterialTheme.typography.displayMedium,
                            fontSize = 20.sp
                        )
                    }
                }
            }
            ButtonComponent(
                textId = R.string.confirm,
                onButtonClicked = { /*TODO*/ }
            )
        }
    }
}