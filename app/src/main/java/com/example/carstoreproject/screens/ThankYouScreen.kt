package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.carstoreproject.R
import com.example.carstoreproject.components.ButtonComponent
import com.example.carstoreproject.navigation.Screen

@Composable
fun ThankYouScreen(
    navController: NavController
) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation_1716375248757))
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.size(250.dp)
        )
        Text(
            text = stringResource(R.string.thanks_for_order),
            style = MaterialTheme.typography.headlineLarge
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.medium_padding))
        ) {
            ButtonComponent(
                textId = R.string.continue_,
                onButtonClicked = {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                },
            )
        }
    }

}