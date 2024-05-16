package com.example.carstoreproject.data.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserViewModel: ViewModel() {
    private val _user = mutableStateOf<User?>(null)
    val user: MutableState<User?> = _user

    init {
        fetchUserData()
    }

    private fun fetchUserData() {
        val uid = FirebaseAuth.getInstance().currentUser?.uid
        if (uid != null) {
            val database =
                FirebaseDatabase.getInstance("https://carstoreproject-9d352-default-rtdb.europe-west1.firebasedatabase.app/")
            val userRef = database.getReference("users").child(uid)

            userRef.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)
                    _user.value = user
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })
        }
    }
    fun updateUserData(firstName: String, lastName: String, email: String) {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val profileUpdates = UserProfileChangeRequest.Builder()
                .setDisplayName("$firstName $lastName")
                .build()

            currentUser.updateProfile(profileUpdates)
                .addOnSuccessListener {
                    currentUser.updateEmail(email)
                        .addOnSuccessListener {
                            val database = FirebaseDatabase.getInstance("https://carstoreproject-9d352-default-rtdb.europe-west1.firebasedatabase.app/")
                            val userRef = database.getReference("users").child(currentUser.uid)
                            val updateUser = User(firstName, lastName, email)

                            userRef.setValue(updateUser)
                                .addOnSuccessListener {
                                    _user.value = updateUser
                                }
                                .addOnFailureListener { exception ->
                                }
                        }
                        .addOnFailureListener { exception ->
                        }
                }
                .addOnFailureListener { exception ->
                }
        }
    }
}