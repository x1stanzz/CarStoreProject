package com.example.carstoreproject.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.carstoreproject.R
import com.example.carstoreproject.components.DataTextField
import com.example.carstoreproject.data.viewmodels.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

@Composable
fun ProfileScreen() {
    val userViewModel: UserViewModel = viewModel()
    val user by userViewModel.user

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    val imageUri = rememberSaveable { mutableStateOf("")}
    val painter = rememberAsyncImagePainter(
        if(imageUri.value.isEmpty()) {
            R.drawable.ic_profile
        } else {
            imageUri.value
        }
    )

    var isModified by remember { mutableStateOf(false) }
    var showMessage by remember { mutableStateOf(false) }

    LaunchedEffect(user) {
        user?.let {
            firstName = it.firstName
            lastName = it.lastName
            email = it.email
            imageUri.value = it.imageUri
        }
    }
    Column {
        Text(
            text = stringResource(R.string.profile),
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        )
        Divider(color = MaterialTheme.colorScheme.onBackground)
        ProfileImage(imageUri, painter) {
            isModified = true
        }
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding))
        ) {
            user?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.extra_small_padding))

                ) {
                    DataTextField(
                        icon = Icons.Outlined.Person,
                        labelId = R.string.first_name,
                        data = firstName,
                        onValueChange = {
                            firstName = it
                            isModified = true
                        },
                        isReadOnly = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.extra_small_padding))
                ) {
                    DataTextField(
                        icon = Icons.Outlined.Person,
                        labelId = R.string.last_name,
                        data = lastName,
                        onValueChange = {
                            lastName = it
                            isModified = true
                        },
                        isReadOnly = false,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = dimensionResource(R.dimen.extra_small_padding))
                ) {
                    DataTextField(
                        icon = Icons.Outlined.Email,
                        labelId = R.string.email,
                        data = email,
                        onValueChange = {
                            email = it
                        },
                        isReadOnly = true,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        onClick = {
                            userViewModel.updateUserData(firstName, lastName, email, imageUri.value)
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

@Composable
fun ProfileImage(
    imageUri: MutableState<String>,
    painter: AsyncImagePainter,
    onImageChange: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let { imageUri.value = it.toString() }
    }
    Column(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.small_padding))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            shape = CircleShape,
            modifier = Modifier
                .padding(dimensionResource(R.dimen.small_padding))
                .size(100.dp)
                .clickable {
                    launcher.launch("image/*")
                    onImageChange()
                }
        ) {
            Box() {
                Image(
                    painter = painter,
                    contentDescription = null,
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
        }
        Text(
            text = stringResource(R.string.change_profile_picture),
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.clickable {
                launcher.launch("image/*")
                onImageChange()
            }
        )
    }
}

fun getCurrentUser(): FirebaseUser? {
    val auth = FirebaseAuth.getInstance()
    return auth.currentUser
}