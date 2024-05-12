package com.example.carstoreproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class AuthScreen {
    object SignUpAuthScreen : AuthScreen()
    object TermsAndConditionsAuthScreen : AuthScreen()
    object SignInAuthScreen : AuthScreen()
    object MainAuthScreen : AuthScreen()
    object ForgotPasswordAuthScreen : AuthScreen()
}

object AcceleratoRouter {
    var currentAuthScreen: MutableState<AuthScreen> = mutableStateOf(AuthScreen.SignUpAuthScreen)

    fun navigateTo(destination : AuthScreen) {
        currentAuthScreen.value = destination
    }
}