package com.example.carstoreproject.models

data class Car (
    val brand: String? = null,
    val brandLogo: String? = null,
    val image: String? = null,
    val images: Map<String, String>? = null,
    val name: String? = null,
    val price: Int? = null,
    val year: Int? = null,
    val color: String? = null,
    val maxSpeed: Int? = null,
    val kmpl: Double? = null,
    val engineSize: Int? = null,
    val numberOfSeats: Int? = null,
    val engineType: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
)