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
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.components.SearchField
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.models.Car

@Composable
fun SearchResultScreen(
    initialQuery: String,
    cars: List<Car>,
    navController: NavController,
    userViewModel: UserViewModel
) {
    var query by remember { mutableStateOf(initialQuery) }
     val filteredCars = cars.filter { it.name!!.contains(query, true)}
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium_padding))
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
        SearchField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.medium_padding))
        ) { query ->
            navController.navigate("search_results/$query") {
                popUpTo(navController.graph.startDestinationId)
            }
        }
        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_padding))
        )
        LazyColumn(
            modifier = Modifier.padding(
                horizontal = dimensionResource(R.dimen.medium_padding),
                vertical = dimensionResource(R.dimen.small_padding)
            )
        ) {
            items(filteredCars) { car ->
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