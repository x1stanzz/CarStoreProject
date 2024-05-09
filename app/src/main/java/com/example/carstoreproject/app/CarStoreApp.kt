package com.example.carstoreproject.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import com.example.carstoreproject.R
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.Screen
import com.example.carstoreproject.screens.ForgotPasswordScreen
import com.example.carstoreproject.screens.HomeScreen
import com.example.carstoreproject.screens.SignInScreen
import com.example.carstoreproject.screens.SignUpScreen
import com.example.carstoreproject.screens.TermsAndConditionsScreen

@Composable
fun CarStoreApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.medium_padding))
        ,
        color = Color.White
    ) {
        Crossfade(targetState = AcceleratoRouter.currentScreen) { currentState ->
            when(currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen()
                }
                is Screen.TermsAndConditionsScreen -> {
                    TermsAndConditionsScreen()
                }
                is Screen.SignInScreen -> {
                    SignInScreen()
                }
                is Screen.HomeScreen -> {
                    HomeScreen()
                }
                is Screen.ForgotPasswordScreen -> {
                    ForgotPasswordScreen()
                }
            }
            
        }

    }
}