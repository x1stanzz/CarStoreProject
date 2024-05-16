package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.carstoreproject.datastate.CarsDataState
import com.example.carstoreproject.models.Car
import com.example.carstoreproject.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    carsViewModel: CarsViewModel,
    navController: NavController
) {
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.medium_padding))
            .fillMaxWidth()
    ) {
        LogoImage(
            imageId = R.drawable.accelerato_logo,
            modifier = Modifier
                .width(200.dp)
                .height(75.dp)
                .align(Alignment.CenterHorizontally)
        )
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            SearchField(modifier = Modifier.fillMaxWidth())
        }
        SetData(
            viewModel = carsViewModel,
            navController = navController
        )
    }
}

@Composable
fun SetData(
    viewModel: CarsViewModel,
    navController: NavController,
    modifier: Modifier = Modifier
    ) {
    when(val result = viewModel.response.value) {
        is CarsDataState.Success -> {
            ShowBrands(
                result.data,
                modifier = modifier
            )
            ShowCars(
                cars = result.data,
                navController = navController
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
                ,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
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
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(R.string.cars),
        style = MaterialTheme.typography.headlineSmall,
        modifier = Modifier.padding(dimensionResource(R.dimen.extra_small_padding))
    )
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
    ) {
        items(cars) { car ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.onSecondary
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.extra_small_padding))
                    .height(180.dp)
                    .clickable {
                        val brand = car.brand
                        val carName = car.name
                        val carPrice = car.price
                        val carYear = car.year
                        navController.navigate(
                            Screen.CarDetailScreen.createRoute(
                                car.brand!!,
                                car.name!!,
                                car.price!!,
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
                    ){
                        AsyncImage(
                            model = car.image,
                            contentDescription = car.name,
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .height(120.dp),
                            alignment = Alignment.Center
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(dimensionResource(R.dimen.small_padding))
                            .fillMaxHeight(),
                        horizontalAlignment = Alignment.End
                    ) {
                        Text(
                            text = car.name!!,
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.End
                        )
                        Text(
                            text = car.year!!.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )
                        AsyncImage(
                            model = car.brandLogo,
                            contentDescription = car.brand,
                            modifier = Modifier
                                .height(40.dp)
                                .padding(top = dimensionResource(R.dimen.small_padding))
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Box(modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(MaterialTheme.colorScheme.primary)
                        ) {
                            Text(
                                text = "$${car.price!!}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSecondary,
                                modifier = Modifier
                                    .padding(dimensionResource(R.dimen.small_padding))
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
        navController = NavController(LocalContext.current.applicationContext)
    )
}