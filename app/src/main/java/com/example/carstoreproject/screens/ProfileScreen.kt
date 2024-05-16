package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carstoreproject.R
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(user: FirebaseUser?) {
    val userViewModifier: UserViewModel = viewModel()
    val user by userViewModifier.user
    Column(modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))) {
        Text(text = stringResource(R.string.profile), style = MaterialTheme.typography.headlineLarge)
        user?.let {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Text(text = "First Name:", style = MaterialTheme.typography.bodyLarge)
                Text(text = it.firstName, style = MaterialTheme.typography.titleMedium)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Text(text = "Last Name:", style = MaterialTheme.typography.bodyLarge)
                Text(text = it.lastName, style = MaterialTheme.typography.titleMedium)
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Text(text = "Email:", style = MaterialTheme.typography.bodyLarge)
                Text(text = it.email, style = MaterialTheme.typography.titleMedium)
            }
        } ?: run {
            Text(text = "No user is currently signed in.")
        }
    }
}

fun getCurrentUser(): FirebaseUser? {
    val auth = FirebaseAuth.getInstance()
    return auth.currentUser
}