package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
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
import com.example.carstoreproject.components.CustomizedTextField
import com.example.carstoreproject.components.LoginSignUpTextComponent
import com.example.carstoreproject.components.LogoImage
import com.example.carstoreproject.components.TextDivider
import com.example.carstoreproject.components.UnderlinedText
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.Screen

@Composable
fun SignInScreen(
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            LogoImage(
                imageId = R.drawable.car_store_logo,
                modifier = modifier
            )
            Text(
                text = stringResource(R.string.sign_into_account),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.small_padding))
            )
            CustomizedTextField(
                icon = Icons.Outlined.Person,
                labelId = R.string.email,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
                onTextSelected = {

                }
            )
            CustomizedTextField(
                icon = Icons.Outlined.Lock,
                labelId = R.string.password,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                onTextSelected = {

                },
                isPassword = true
            )
            UnderlinedText(
                textId = R.string.forgot_password,
                textColor = Color(0xff2596BE),
            )
            ButtonComponent(
                textId = R.string.log_in,
                onButtonClicked = {

                }
            )
            TextDivider()
            LoginSignUpTextComponent(
                initialTextId = R.string.not_registered_yet,
                clickableTextId = R.string.create_an_account,
                onTextSelected = {
                    AcceleratoRouter.navigateTo(Screen.SignUpScreen)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}