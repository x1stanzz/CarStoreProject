package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carstoreproject.R
import com.example.carstoreproject.components.DataTextField
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen(user: FirebaseUser?) {
    val userViewModel: UserViewModel = viewModel()
    val user by userViewModel.user

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }

    var isModified by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        user?.let {
            firstName = it.firstName
            lastName = it.lastName
            email = it.email
        }
    }
    Column(modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))) {
        Text(text = stringResource(R.string.profile), style = MaterialTheme.typography.headlineLarge)
        user?.let {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))

            ) {
                Text(
                    text = "First Name:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.alignByBaseline()
                )
                DataTextField(
                    data = firstName,
                    onValueChange = {
                        firstName = it
                        isModified = true
                    },
                    isReadOnly = false,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Divider(color = MaterialTheme.colorScheme.outline)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Text(
                    text = "Last Name:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.alignBy(LastBaseline)
                )
                DataTextField(
                    data = lastName,
                    onValueChange = {
                        lastName = it
                        isModified = true
                    },
                    isReadOnly = false,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Divider(color = MaterialTheme.colorScheme.outline)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.medium_padding))
            ) {
                Text(
                    text = "Email:",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.alignBy(LastBaseline)
                )
                DataTextField(
                    data = email,
                    onValueChange = {
                        email = it
                    },
                    isReadOnly = true,
                    modifier = Modifier.alignByBaseline()
                )
            }
            Divider(color = MaterialTheme.colorScheme.outline)
            Spacer(modifier = Modifier.weight(1f))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        userViewModel.updateUserData(firstName, lastName, email)
                        isModified = false
                        showMessage = true
                    },
                    enabled = isModified
                ) {
                    Text(text = stringResource(R.string.save_changes))
                }
            }
        } ?: run {
            Text(text = "No user is currently signed in.")
        }
        if(showMessage) {
            Snackbar(
                action = {
                    TextButton(onClick = { showMessage = false }) {
                        Text(text = stringResource(R.string.dismiss))
                    }
                }
            ) {
                Text(text = stringResource(R.string.change_saved))
            }
        }
    }
}

fun getCurrentUser(): FirebaseUser? {
    val auth = FirebaseAuth.getInstance()
    return auth.currentUser
}