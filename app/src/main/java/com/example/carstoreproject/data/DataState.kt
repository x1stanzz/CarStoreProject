package com.example.carstoreproject.data

import com.example.carstoreproject.models.Brand

 sealed class DataState {
    class Success(val data: MutableList<Brand>): DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object  Empty : DataState()

}