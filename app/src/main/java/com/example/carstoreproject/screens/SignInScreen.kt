package com.example.carstoreproject.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carstoreproject.R
import com.example.carstoreproject.components.ButtonComponent
import com.example.carstoreproject.components.CustomizedTextButton
import com.example.carstoreproject.components.CustomizedTextField
import com.example.carstoreproject.components.LoginSignUpTextComponent
import com.example.carstoreproject.components.LogoImage
import com.example.carstoreproject.components.TextDivider
import com.example.carstoreproject.data.login.LoginUIEvent
import com.example.carstoreproject.data.login.LoginViewModel
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.AuthScreen

@Composable
fun SignInScreen(
    loginViewModel: LoginViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var showError by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.medium_padding)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                LogoImage(
                    imageId = R.drawable.accelerato_logo,
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
                        loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                    },
                    isError = loginViewModel.loginValidationState.value.emailError,
                    errorMessage = R.string.email_error,
                    showError = showError
                )
                CustomizedTextField(
                    icon = Icons.Outlined.Lock,
                    labelId = R.string.password,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    onTextSelected = {
                        loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                    },
                    isPassword = true,
                    isError = loginViewModel.loginValidationState.value.passwordError,
                    errorMessage = R.string.invalid_password,
                    showError = showError
                )
                CustomizedTextButton(
                    textId = R.string.forgot_password,
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.ForgotPasswordButtonCLicked)
                    }
                )
                ButtonComponent(
                    textId = R.string.log_in,
                    onButtonClicked = {
                        loginViewModel.onEvent(LoginUIEvent.LoginButtonClicked)
                        showError = true
                    }
                )
                TextDivider()
                LoginSignUpTextComponent(
                    initialTextId = R.string.not_registered_yet,
                    clickableTextId = R.string.create_an_account,
                    onTextSelected = {
                        AcceleratoRouter.navigateTo(AuthScreen.SignUpAuthScreen)
                    }
                )
            }
        }

        if(loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
}

@Preview
@Composable
fun SignInScreenPreview() {
    SignInScreen()
}