package com.example.carstoreproject.data.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.datastate.CarsDataState
import com.example.carstoreproject.models.Car
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CarsViewModel: ViewModel() {
    val response: MutableState<CarsDataState> = mutableStateOf(CarsDataState.Empty)
    val carsList: MutableList<Car> = mutableListOf()

    init {
        fetchDataFromDatabase()
    }

    private fun fetchDataFromDatabase() {
        response.value = CarsDataState.Loading
        FirebaseDatabase.getInstance("https://carstoreproject-9d352-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Car")
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(carsSnapshot in snapshot.children) {
                        val carItem = carsSnapshot.getValue(Car::class.java)
                        if(carItem != null)
                            carsList.add(carItem)
                    }
                    response.value = CarsDataState.Success(carsList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = CarsDataState.Failure(error.message)
                }
            })
    }
}