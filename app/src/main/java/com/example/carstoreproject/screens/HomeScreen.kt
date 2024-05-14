package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.carstoreproject.R
import com.example.carstoreproject.data.DataState
import com.example.carstoreproject.data.viewmodels.BrandsViewModel
import com.example.carstoreproject.models.Brand

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: BrandsViewModel) {
    Column {
        TopAppBar(
            title = { /*TODO*/ },
            colors = topAppBarColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                titleContentColor = MaterialTheme.colorScheme.primary
            )
        )
        SetData(viewModel = viewModel)
    }
}

@Composable
fun SetData(
    viewModel: BrandsViewModel,
    modifier: Modifier = Modifier
    ) {
    when(val result = viewModel.response.value) {
        is DataState.Success -> {
            ShowBrands(
                result.data,
                modifier = modifier
            )
        }
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is DataState.Failure -> {
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
    brands: MutableList<Brand>,
    modifier: Modifier = Modifier
    ) {
    Text(
        text = stringResource(R.string.brands),
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.medium_padding),
                bottom = dimensionResource(R.dimen.small_padding),
                start = dimensionResource(R.dimen.medium_padding)
            )
    )
    LazyRow {
        items(brands) { brand ->
            Card(
                modifier = Modifier
                    .size(75.dp)
                    .padding(
                        start = dimensionResource(R.dimen.medium_padding),
                        top = dimensionResource(R.dimen.small_padding)
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFe6e6e6)
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
                        model = brand.image,
                        contentDescription = brand.name,
                        alignment = Alignment.Center,
                    )
                }
            }
        }
    }
}