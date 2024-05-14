package com.example.carstoreproject.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.AuthScreen
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
        color = MaterialTheme.colorScheme.background
    ) {
        Crossfade(targetState = AcceleratoRouter.currentAuthScreen) { currentState ->
            when(currentState.value) {
                is AuthScreen.SignUpAuthScreen -> {
                    SignUpScreen()
                }
                is AuthScreen.TermsAndConditionsAuthScreen -> {
                    TermsAndConditionsScreen()
                }
                is AuthScreen.SignInAuthScreen -> {
                    SignInScreen()
                }
                is AuthScreen.MainAuthScreen -> {
                    MainScreen()
                }
                is AuthScreen.ForgotPasswordAuthScreen -> {
                    ForgotPasswordScreen()
                }
            }
            
        }

    }
}