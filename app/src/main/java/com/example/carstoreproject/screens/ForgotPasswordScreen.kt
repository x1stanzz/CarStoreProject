package com.example.carstoreproject.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.carstoreproject.R
import com.example.carstoreproject.components.LogoImage


@Composable
fun ForgotPasswordScreen () {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(dimensionResource(R.dimen.medium_padding))
    ) {
        Column (
            modifier = Modifier.fillMaxSize()
        ) {
            LogoImage(
                imageId = R.drawable.accelerato_logo,
            )
        }
    }
}