package com.example.carstoreproject.navigation

sealed class Screen (
    val route: String
) {
    object HomeScreen : Screen(route = "home")
    object SearchScreen : Screen(route = "search")
    object FavouriteScreen : Screen(route = "favourite")
    object SettingsScreen : Screen(route = "setting")
    object CarDetailScreen : Screen(route = "car_detail/{brand}/{name}/{price}/{year}") {
        fun createRoute(
            brand: String,
            name: String,
            price: Int,
            year: Int
        ) = "car_detail/$brand/$name/$price/$year"
    }
    object BrandCarsScreen : Screen(route = "brand_cars/{brand}") {
        fun createRoute(brand: String) = "brand_cars/$brand"
    }
}