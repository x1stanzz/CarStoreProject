package com.example.carstoreproject.navigation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Screen {
    object SignUpScreen : Screen()
    object TermsAndConditionsScreen : Screen()
    object SignInScreen : Screen()
    object HomeScreen : Screen()
}

object AcceleratoRouter {
    var currentScreen: MutableState<Screen> = mutableStateOf(Screen.SignUpScreen)

    fun navigateTo(destination : Screen) {
        currentScreen.value = destination
    }
}