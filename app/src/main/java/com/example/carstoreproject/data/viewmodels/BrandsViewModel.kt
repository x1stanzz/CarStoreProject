package com.example.carstoreproject.data.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.data.DataState
import com.example.carstoreproject.models.Brand
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class BrandsViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        val tempList = mutableListOf<Brand>()
        response.value = DataState.Loading
        FirebaseDatabase.getInstance("https://carstoreproject-9d352-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Brand")
            .addListenerForSingleValueEvent(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    for(DataSnap in snapshot.children) {
                        val brandItem = DataSnap.getValue(Brand::class.java)
                        if(brandItem != null)
                            tempList.add(brandItem)
                    }
                    response.value = DataState.Success(tempList)
                }

                override fun onCancelled(error: DatabaseError) {
                    response.value = DataState.Failure(error.message)
                }
            })
    }
}