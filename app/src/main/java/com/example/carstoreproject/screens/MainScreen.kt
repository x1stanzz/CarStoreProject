package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.carstoreproject.data.viewmodels.CarsViewModel
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.example.carstoreproject.navigation.Screen

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    carsViewModel: CarsViewModel = viewModel(),
    userViewModel: UserViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            icon = Icons.Filled.Home,
            route = Screen.HomeScreen.route
        ),
        BottomNavigationItem(
            title = "Favorite",
            icon = Icons.Filled.Star,
            route = Screen.FavouriteScreen.route
        ),
        BottomNavigationItem(
            title = "Profile",
            icon = Icons.Filled.AccountCircle,
            route = Screen.SettingsScreen.route
        )
    )
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    val navBackStackEntry by navigationController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        bottomBar = {
            if (
                currentRoute != Screen.CarDetailScreen.route &&
                currentRoute != Screen.MapScreen.route &&
                currentRoute != Screen.PurchaseConfirmationScreen.route
                ) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.background
                ) {
                    items.forEachIndexed() { index, item ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = {
                                selectedItem = index
                                navigationController.navigate(item.route)
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(text = item.title)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.primary,
                                selectedTextColor = MaterialTheme.colorScheme.primary,
                                indicatorColor = MaterialTheme.colorScheme.background,
                                unselectedTextColor = MaterialTheme.colorScheme.onBackground,
                                unselectedIconColor = MaterialTheme.colorScheme.onBackground
                            )
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(
                    carsViewModel = carsViewModel,
                    navController = navigationController,
                    userViewModel = userViewModel
                )
            }
            composable(Screen.SearchResultScreen.route) { backStackEntry ->
                val query = backStackEntry.arguments?.getString("query") ?: ""
                SearchResultScreen(
                    initialQuery = query,
                    cars = carsViewModel.carsList,
                    navController = navigationController,
                    userViewModel = userViewModel
                )
            }
            composable(Screen.FavouriteScreen.route) {
                FavouriteScreen(
                    userViewModel = userViewModel,
                    carsViewModel = carsViewModel,
                    navController = navigationController
                )
            }
            composable(Screen.SettingsScreen.route) {
                ProfileScreen()
            }
            composable(
                route = Screen.CarDetailScreen.route,
                arguments = listOf(
                    navArgument("brand") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("price") { type = NavType.IntType },
                    navArgument("year") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val brand = backStackEntry.arguments?.getString("brand")
                val name = backStackEntry.arguments?.getString("name")
                val price = backStackEntry.arguments?.getInt("price")
                val year = backStackEntry.arguments?.getInt("year")
                val selectedCar = carsViewModel.carsList
                    .firstOrNull { car -> car.brand == brand && car.name == name && car.price == price && car.year == year }
                if (selectedCar != null) {
                    CarDetailScreen(
                        car = selectedCar,
                        navController = navigationController,
                        userViewModel = userViewModel,
                        carsViewModel = carsViewModel
                    )
                }
            }
            composable(
                route = Screen.PurchaseConfirmationScreen.route,
                arguments = listOf(
                    navArgument("brand") { type = NavType.StringType },
                    navArgument("name") { type = NavType.StringType },
                    navArgument("price") { type = NavType.IntType },
                    navArgument("year") { type = NavType.IntType }
                )
            ) { backStackEntry ->
                val brand = backStackEntry.arguments?.getString("brand")
                val name = backStackEntry.arguments?.getString("name")
                val price = backStackEntry.arguments?.getInt("price")
                val year = backStackEntry.arguments?.getInt("year")

                val selectedCar = carsViewModel.carsList.firstOrNull { car ->
                    car.brand == brand && car.name == name && car.price == price && car.year == year
                }

                PurchaseConfirmationScreen(
                    car = selectedCar!!,
                    navController = navigationController,
                    carsViewModel = carsViewModel,
                    userViewModel = userViewModel
                )
            }
            composable(
                route = Screen.BrandCarsScreen.route,
                arguments = listOf(navArgument("brand") {type = NavType.StringType} )
                ) {backStackEntry ->
                val brand = backStackEntry.arguments?.getString("brand")
                if(brand != null) {
                    BrandScreen(
                        brand = brand,
                        carsViewModel = carsViewModel,
                        navController = navigationController,
                        userViewModel = userViewModel
                    )
                }
            }
            composable(
                route = Screen.MapScreen.route,
                arguments = listOf(
                    navArgument("latitude") { type = NavType.FloatType },
                    navArgument("longitude") { type = NavType.FloatType }
                )
                ) { backStackEntry ->
                val latitude = backStackEntry.arguments?.getFloat("latitude")?.toDouble() ?: 0.0
                val longitude = backStackEntry.arguments?.getFloat("longitude")?.toDouble() ?: 0.0
                MapScreen(
                    navController = navigationController,
                    latitude = latitude,
                    longitude = longitude
                )
            }
            composable(route = Screen.ThankYouScreen.route) {
                ThankYouScreen(navController = navigationController)
            }
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        carsViewModel = viewModel()
    )
}