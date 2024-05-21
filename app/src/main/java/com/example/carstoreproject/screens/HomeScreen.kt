package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.carstoreproject.R
import com.example.carstoreproject.components.LogoImage
import com.example.carstoreproject.components.SearchField
import com.example.carstoreproject.data.viewmodels.CarsViewModel
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.datastate.CarsDataState
import com.example.carstoreproject.models.Car
import com.example.carstoreproject.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    carsViewModel: CarsViewModel,
    userViewModel: UserViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_padding))
            .fillMaxWidth()
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                LogoImage(
                    imageId = R.drawable.accelerato_logo,
                    modifier = Modifier
                        .width(200.dp)
                        .height(75.dp)
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                SearchField(modifier = Modifier.fillMaxWidth()) { query ->
                    navController.navigate("search_results/$query")
                }
            }
        }
        item {
            SetData(
                viewModel = carsViewModel,
                navController = navController,
                userViewModel = userViewModel,
            )
        }
    }
}

@Composable
fun SetData(
    viewModel: CarsViewModel,
    navController: NavController,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
    ) {
    when(val result = viewModel.response.value) {
        is CarsDataState.Success -> {
            ShowBrands(
                result.data,
                navController = navController,
                modifier = modifier
            )
            ShowCars(
                cars = result.data,
                navController = navController,
                userViewModel = userViewModel
            )
        }
        is CarsDataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is CarsDataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = result.message)
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Error fetching data")
            }
        }
    }
}

@Composable
fun ShowBrands(
    cars: MutableList<Car>,
    navController: NavController,
    modifier: Modifier = Modifier
    ) {
    Text(
        text = stringResource(R.string.brands),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
    )
    val uniqueCars = cars.distinctBy { it.brandLogo }
    LazyRow(
        contentPadding = PaddingValues(
            start = 0.dp,
            end = dimensionResource(R.dimen.small_padding))
    ) {
        items(uniqueCars) { car ->
            Card(
                modifier = Modifier
                    .size(64.dp)
                    .padding(dimensionResource(R.dimen.extra_small_padding))
                    .clickable {
                        navController.navigate(Screen.BrandCarsScreen.createRoute(car.brand!!))
                    }
                ,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.inverseOnSurface
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                )
            ) {
                Box(modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.extra_small_padding)),
                    contentAlignment = Alignment.Center) {
                    AsyncImage(
                        model = car.brandLogo,
                        contentDescription = car.brand,
                        alignment = Alignment.Center,
                    )
                }
            }
        }
    }
}

@Composable
fun ShowCars(
    cars: MutableList<Car>,
    navController: NavController,
    userViewModel: UserViewModel,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.cars),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        cars.forEach() { car ->
            CarCard(
                car = car,
                navController = navController,
                isFavorite = userViewModel.isFavorite(car.name!!),
                onFavoriteClick = { userViewModel.toggleFavoriteCar(it) }
            )
        }
    }
}

@Composable
fun CarCard(
    car: Car,
    navController: NavController,
    onFavoriteClick: (String) -> Unit,
    isFavorite: Boolean
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.extra_small_padding))
            .height(120.dp)
            .clickable {
                val brand = car.brand
                val carName = car.name
                val carPrice = car.price
                val carYear = car.year
                navController.navigate(
                    Screen.CarDetailScreen.createRoute(
                        brand!!,
                        carName!!,
                        carPrice!!,
                        carYear!!
                    )
                )
            }
    ) {
        Row {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                contentAlignment = Alignment.Center,
            ) {
                AsyncImage(
                    model = car.image,
                    contentDescription = car.name,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth() ,alignment = Alignment.Center
                )
            }
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .padding(dimensionResource(R.dimen.small_padding))
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = car.name!!,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = car.year!!.toString(),
                    style = MaterialTheme.typography.bodySmall
                )
                AsyncImage(
                    model = car.brandLogo,
                    contentDescription = car.brand,
                    modifier = Modifier
                        .height(30.dp)
                        .padding(top = dimensionResource(R.dimen.small_padding))
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$${car.price!!}",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Box(
                        modifier = Modifier.size(48.dp)
                    ) {
                        IconButton(onClick = { onFavoriteClick(car.name) }) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.StarOutline,
                                contentDescription = "Favorite",
                                tint = Color(0xFFE5B80B),
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        carsViewModel = viewModel(),
        navController = NavController(LocalContext.current.applicationContext),
        userViewModel = viewModel()
    )
}