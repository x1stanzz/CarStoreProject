package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.data.viewmodels.CarsViewModel
import com.example.carstoreproject.data.viewmodels.UserViewModel

@Composable
fun FavouriteScreen(
    userViewModel: UserViewModel,
    carsViewModel: CarsViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
    ) {
        val favoriteCarIds = userViewModel.user.value?.favoriteCars ?: listOf()
        val favoriteCars = carsViewModel.carsList.filter { favoriteCarIds.contains(it.name) }
        Text(
            text = stringResource(R.string.favorites),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        )
        Divider(color = MaterialTheme.colorScheme.onBackground)
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        ) {
            favoriteCars.forEach {car ->
                CarCard(
                    car = car,
                    navController = navController,
                    onFavoriteClick = { userViewModel.toggleFavoriteCar(it) },
                    isFavorite = true
                )
            }
        }
    }
}