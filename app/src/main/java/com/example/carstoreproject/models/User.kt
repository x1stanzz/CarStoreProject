package com.example.carstoreproject.models

data class User (
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val imageUri: String = "",
    val favoriteCars: List<String> = listOf()
)