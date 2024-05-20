package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.models.Car

@Composable
fun SearchResultScreen(
    query: String,
    cars: List<Car>,
    navController: NavController,
    userViewModel: UserViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null
                )
            }
            Text(
                text = stringResource(R.string.search_results),
                style = MaterialTheme.typography.headlineLarge
            )
        }
        LazyColumn {
            items(cars.filter { it.name!!.contains(query, true)}) { car ->
                CarCard(
                    car = car,
                    navController = navController,
                    onFavoriteClick = { userViewModel.toggleFavoriteCar(car.name!!)},
                    isFavorite = userViewModel.isFavorite(car.name!!)
                )
            }
        }
    }
}