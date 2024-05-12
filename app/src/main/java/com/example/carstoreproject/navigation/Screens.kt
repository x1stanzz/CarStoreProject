package com.example.carstoreproject.navigation

sealed class Screen (
    val route: String
) {
    object HomeScreen : Screen(route = "home")
    object SearchScreen : Screen(route = "search")
    object FavouriteScreen : Screen(route = "favourite")
    object SettingsScreen : Screen(route = "setting")
}