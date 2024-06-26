package com.example.carstoreproject.navigation

sealed class Screen (
    val route: String
) {
    object HomeScreen : Screen(route = "home")
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
    object MapScreen : Screen(route = "map/{latitude}/{longitude}") {
        fun createRoute(latitude: Double?, longitude: Double?) = "map/$latitude/$longitude"
    }

    object SearchResultScreen : Screen(route = "search_results/{query}") {
        fun createRoute(query: String) = "search_results/$query"
    }
    object PurchaseConfirmationScreen : Screen(route = "purchase_confirmation/{brand}/{name}/{price}/{year}") {
        fun createRoute(
            brand: String,
            name: String,
            price: Int,
            year: Int
        ) = "purchase_confirmation/$brand/$name/$price/$year"
    }

    object ThankYouScreen : Screen(route = "thank_you")
}