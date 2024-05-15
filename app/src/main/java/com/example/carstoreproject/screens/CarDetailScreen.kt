package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.EventSeat
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.carstoreproject.R
import com.example.carstoreproject.models.Car

@Composable
fun CarDetailScreen(
    car: Car,
    navController: NavController
) {
    Column {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null
            )
        }
        CarSpecRow(car = car)
    }
}

@Composable
fun CarSpecRow(car: Car) {
    Row {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            items(4) { index ->
                SpecificationCard(
                    icon = when(index) {
                        0 -> Icons.Filled.Speed
                        1 -> Icons.Filled.LocalGasStation
                        2 -> Icons.Filled.BarChart
                        3 -> Icons.Filled.EventSeat
                        else -> Icons.Filled.Info

                    },
                    title = when(index) {
                        0 -> stringResource(R.string.max_speed)
                        1 -> stringResource(R.string.fuel_consumption)
                        2 -> stringResource(R.string.engine_capacity)
                        3 -> stringResource(R.string.number_of_seats)
                        else -> ""
                    },
                    value = when(index) {
                        0 -> car.maxSpeed.toString() + stringResource(R.string.kmh)
                        1 -> car.kmpl.toString()
                        2 -> car.engineSize.toString() + stringResource(R.string.cc )
                        3 -> car.numberOfSeats.toString()
                        else -> ""
                    }
                )
            }
        }
    }

}

@Composable
fun SpecificationCard(
    icon: ImageVector,
    title: String,
    value: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSecondary
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .width(190.dp)
            .height(80.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(dimensionResource(R.dimen.extra_small_padding))
        ) {
            Column(modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
            }
            Column(
                modifier = Modifier
                    .weight(1.5f)
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}