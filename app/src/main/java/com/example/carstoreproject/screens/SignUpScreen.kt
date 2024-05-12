package com.example.carstoreproject.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.carstoreproject.R
import com.example.carstoreproject.components.ButtonComponent
import com.example.carstoreproject.components.CheckboxComponent
import com.example.carstoreproject.components.CustomizedTextField
import com.example.carstoreproject.components.LoginSignUpTextComponent
import com.example.carstoreproject.components.LogoImage
import com.example.carstoreproject.components.TextDivider
import com.example.carstoreproject.data.signup.SignUpUIEvent
import com.example.carstoreproject.data.signup.SignUpViewModel
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.AuthScreen

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SignUpScreen(
    signUpViewModel: SignUpViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize().padding(dimensionResource(R.dimen.medium_padding)),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            color = Color.White,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = modifier.fillMaxSize()
            ) {
                LogoImage(
                    imageId = R.drawable.accelerato_logo,
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
                    imeAction = ImeAction.Next,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.FirstNameChanged(it))
                    },
                    isError = signUpViewModel.signUpValidationState.value.firstNameError,
                    errorMessage = R.string.name_field_error,
                    showError = showError
                )
                CustomizedTextField(
                    icon = Icons.Outlined.Person,
                    labelId = R.string.last_name,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.LastNameChanged(it))
                    },
                    isError = signUpViewModel.signUpValidationState.value.lastNameError,
                    errorMessage = R.string.name_field_error,
                    showError = showError
                )
                CustomizedTextField(
                    icon = Icons.Outlined.Email,
                    labelId = R.string.email,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.EmailChanged(it))
                    },
                    isError = signUpViewModel.signUpValidationState.value.emailError,
                    errorMessage = R.string.email_error,
                    showError = showError
                )
                CustomizedTextField(
                    icon = Icons.Outlined.Lock,
                    labelId = R.string.password,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.PasswordChanged(it))
                    },
                    isPassword = true,
                    isError = signUpViewModel.signUpValidationState.value.passwordError,
                    errorMessage = R.string.password_error,
                    showError = showError
                )
                CustomizedTextField(
                    icon = Icons.Outlined.Lock,
                    labelId = R.string.confirm_password,
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done,
                    onTextSelected = {
                        signUpViewModel.onEvent(SignUpUIEvent.ConfirmPasswordChanged(it))
                    },
                    isPassword = true,
                    isError = signUpViewModel.signUpValidationState.value.confirmPasswordError,
                    errorMessage = R.string.confirm_password_error,
                    showError = showError
                )
                CheckboxComponent(
                    onTextSelected = {
                        AcceleratoRouter.navigateTo(AuthScreen.TermsAndConditionsAuthScreen)
                    },
                    onCheckedChange = {
                        signUpViewModel.onEvent(SignUpUIEvent.PrivacyPolicyCheckBoxClicked(it))
                    }
                )
                ButtonComponent(
                    textId = R.string.sign_up,
                    onButtonClicked = {
                        signUpViewModel.onEvent(SignUpUIEvent.RegisterButtonClicked)
                        showError = true
                    }
                )
                TextDivider()
                LoginSignUpTextComponent(
                    initialTextId = R.string.have_an_account,
                    clickableTextId = R.string.log_in,
                    onTextSelected = {
                        AcceleratoRouter.navigateTo(AuthScreen.SignInAuthScreen)
                    }
                )
            }
        }

        if(signUpViewModel.signUpInProgress.value)
            CircularProgressIndicator()
    }


}

@Preview
@Composable
fun SignUpPreview() {
    SignUpScreen(modifier = Modifier.padding(dimensionResource(R.dimen.medium_padding)))
}