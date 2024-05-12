package com.example.carstoreproject.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.Screen
import com.example.carstoreproject.screens.ForgotPasswordScreen
import com.example.carstoreproject.screens.MainScreen
import com.example.carstoreproject.screens.SignInScreen
import com.example.carstoreproject.screens.SignUpScreen
import com.example.carstoreproject.screens.TermsAndConditionsScreen

@Composable
fun CarStoreApp() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
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
                is Screen.MainScreen -> {
                    MainScreen()
                }
                is Screen.ForgotPasswordScreen -> {
                    ForgotPasswordScreen()
                }
            }
            
        }

    }
}