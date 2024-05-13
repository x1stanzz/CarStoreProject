package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.carstoreproject.navigation.Screen

data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
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
            title = "Search",
            icon = Icons.Filled.Search,
            route = Screen.SearchScreen.route
        ),
        BottomNavigationItem(
            title = "Favorite",
            icon = Icons.Filled.Star,
            route = Screen.FavouriteScreen.route
        ),
        BottomNavigationItem(
            title = "Settings",
            icon = Icons.Filled.Settings,
            route = Screen.SettingsScreen.route
        )
    )
    var selectedItem by rememberSaveable { mutableStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
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
                            selectedIconColor = Color(0xff6789ba),
                            selectedTextColor = Color(0xff6789ba),
                            indicatorColor = Color.White
                        )
                    )
                }
            }
        }
    ) {paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.HomeScreen.route) {
                HomeScreen()
            }
            composable(Screen.SearchScreen.route) {
                SearchScreen()
            }
            composable(Screen.FavouriteScreen.route) {
                FavouriteScreen()
            }
            composable(Screen.SettingsScreen.route) {
                SettingsScreen()
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    MainScreen()
}