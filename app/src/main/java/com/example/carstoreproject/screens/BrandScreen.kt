package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.data.viewmodels.CarsViewModel
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.models.Car

@Composable
fun BrandScreen(
    brand: String,
    carsViewModel: CarsViewModel,
    userViewModel: UserViewModel,
    navController: NavController
) {
    val cars = carsViewModel.carsList.filter { it.brand == brand }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.medium_padding))
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBackIosNew,
                    contentDescription = null
                )
            }
            Text(
                text = brand,
                style = MaterialTheme.typography.headlineLarge
            )
        }
        ShowBrandCars(
            cars = cars,
            navController = navController,
            userViewModel = userViewModel,
            onFavoriteClick = { userViewModel.toggleFavoriteCar(it) }
        )
    }
}

@Composable
fun ShowBrandCars(
    cars: List<Car>,
    navController: NavController,
    userViewModel: UserViewModel,
    onFavoriteClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    cars.forEach { car ->
        CarCard(
            car = car,
            navController = navController,
            onFavoriteClick = onFavoriteClick,
            isFavorite = userViewModel.isFavorite(car.name!!)
        )
    }
}