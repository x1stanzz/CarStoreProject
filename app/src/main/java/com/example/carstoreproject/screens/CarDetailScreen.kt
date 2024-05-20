package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.EventSeat
import androidx.compose.material.icons.filled.FormatPaint
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocalGasStation
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.carstoreproject.R
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.models.Car
import com.example.carstoreproject.navigation.Screen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarDetailScreen(
    car: Car,
    navController: NavController,
    userViewModel: UserViewModel
) {
    val isFavorite = userViewModel.isFavorite(car.name!!)
    Scaffold(
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimensionResource(R.dimen.medium_padding)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    FavoriteButton(
                        onFavoriteClick = { userViewModel.toggleFavoriteCar(car.name) },
                        isFavorite = isFavorite
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    BuyButton(onClick = {

                    })
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
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
                    text = car.name,
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Divider(color = MaterialTheme.colorScheme.onBackground)
            Column(
                modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
            ) {
                CarCarousel(
                    car = car,
                    modifier = Modifier.padding(vertical = dimensionResource(R.dimen.medium_padding))
                )
                Text(
                    text = stringResource(R.string.overview),
                    style = MaterialTheme.typography.headlineSmall
                )
                CarSpecRow(
                    car = car
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.End
                ) {
                    MapLinkButton(
                        onMapClick = { navController.navigate(Screen.MapScreen.createRoute(car.latitude!!, car.longitude!!)) }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CarCarousel(
    car: Car,
    modifier: Modifier = Modifier
) {
    val pagerState = rememberPagerState(pageCount = {
        car.images?.size ?: 0
    })
    Box(
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(1.6f)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxSize()
        ) { page ->
            val imageUrl = car.images?.get("image${page + 1}")
            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(12.dp))
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(dimensionResource(R.dimen.small_padding))
                )
            }
        }
    }
}

@Composable
fun CarSpecRow(
    car: Car,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(vertical = dimensionResource(R.dimen.medium_padding))
    ) {
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.small_padding))
        ) {
            items(6) { index ->
                SpecificationCard(
                    icon = when(index) {
                        0 -> Icons.Filled.FormatPaint
                        1 -> Icons.Filled.Speed
                        2 -> Icons.Filled.LocalGasStation
                        3 -> Icons.Filled.BarChart
                        4 -> Icons.Filled.EventSeat
                        5 -> Icons.Filled.Settings
                        else -> Icons.Filled.Info

                    },
                    value = when(index) {
                        0 -> "${car.color}"
                        1 -> "${car.maxSpeed.toString()} ${stringResource(R.string.kmh)}"
                        2 -> "${car.kmpl.toString()} ${stringResource(R.string.fuel_consumption)}"
                        3 -> "${car.engineSize.toString()} ${stringResource(R.string.cc)}"
                        4 -> "${car.numberOfSeats.toString()} ${stringResource(R.string.number_of_seats)}"
                        5 -> "${car.engineType}"
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
    value: String
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        modifier = Modifier
            .width(75.dp)
            .height(75.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(dimensionResource(R.dimen.extra_small_padding)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = value,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FavoriteButton(
    onFavoriteClick: () -> Unit,
    isFavorite: Boolean
) {
    IconButton(onClick = { onFavoriteClick() },
        modifier = Modifier
            .padding(dimensionResource(R.dimen.extra_small_padding))
        ) {
        Icon(
            imageVector = if (isFavorite) Icons.Default.Star else Icons.Outlined.StarOutline,
            contentDescription = stringResource(R.string.favorites),
            tint = Color(0xFFE5B80B),
            modifier = Modifier
                .size(32.dp)
        )
    }
}

@Composable
fun MapLinkButton(
    onMapClick: () -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = onMapClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.LocationOn,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = stringResource(R.string.view_on_map),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = dimensionResource(R.dimen.extra_small_padding))
        )
    }
}

@Composable
fun BuyButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = { onClick() },
        shape = RoundedCornerShape(dimensionResource(R.dimen.small_padding))
    ) {
        Text(text = stringResource(R.string.buy_now))
    }
}