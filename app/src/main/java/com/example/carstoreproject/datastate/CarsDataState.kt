package com.example.carstoreproject.datastate

import com.example.carstoreproject.models.Car

sealed class CarsDataState {
    class Success(val data: MutableList<Car>): CarsDataState()
    class Failure(val message: String): CarsDataState()
    object Loading: CarsDataState()
    object Empty: CarsDataState()
}