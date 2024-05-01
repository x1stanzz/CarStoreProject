package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.carstoreproject.R
import com.example.carstoreproject.components.ButtonComponent
import com.example.carstoreproject.components.CheckboxComponent
import com.example.carstoreproject.components.CustomizedTextField
import com.example.carstoreproject.components.LoginTextComponent
import com.example.carstoreproject.components.LogoImage
import com.example.carstoreproject.components.PasswordTextField
import com.example.carstoreproject.components.TextDivider
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.Screen

@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            LogoImage(
                imageId = R.drawable.car_store_logo,
                modifier = modifier
            )
            Text(
                text = stringResource(R.string.create_an_account),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.small_padding))
            )
            CustomizedTextField(
                icon = Icons.Outlined.Person,
                labelId = R.string.first_name,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            CustomizedTextField(
                icon = Icons.Outlined.Person,
                labelId = R.string.last_name,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            CustomizedTextField(
                icon = Icons.Outlined.Email,
                labelId = R.string.email,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
            PasswordTextField(
                icon = Icons.Outlined.Lock,
                labelId = R.string.password,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            )
            PasswordTextField(
                icon = Icons.Outlined.Lock,
                labelId = R.string.confirm_password,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            )
            CheckboxComponent(
                textId = R.string.privacy_policy,
                onTextSelected = {
                    AcceleratoRouter.navigateTo(Screen.TermsAndConditionsScreen)
                }
            )
            ButtonComponent(
                textId = R.string.sign_up
            )
            TextDivider()
            LoginTextComponent(
                onTextSelected = {
                    AcceleratoRouter.navigateTo(Screen.SignInScreen)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)))
}