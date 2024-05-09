package com.example.carstoreproject.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.data.rules.LogInValidationState
import com.example.carstoreproject.data.rules.Validator
import com.example.carstoreproject.data.signup.SignUpViewModel
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel : ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    var loginUIState = mutableStateOf(LoginUIState())

    var loginValidationState = mutableStateOf(LogInValidationState())

    var allValidationsPassed = mutableStateOf(false)

    var loginInProgress = mutableStateOf(false)

    fun onEvent(event: LoginUIEvent) {
        when(event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }

            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }

            is LoginUIEvent.LoginButtonClicked -> {
                validateLoginUIDataWithRules()
                if(allValidationsPassed.value) {
                    login(loginUIState.value.email, loginUIState.value.password)
                }
            }

            is LoginUIEvent.ForgotPasswordButtonCLicked -> {
                AcceleratoRouter.navigateTo(Screen.ForgotPasswordScreen)
            }
        }
    }

    private fun login(email: String, password: String) {
        loginInProgress.value = true
        FirebaseAuth
            .getInstance()
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                loginInProgress.value = false
                Log.d(TAG, "Inside_onCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")
                if(it.isSuccessful) {
                    AcceleratoRouter.navigateTo(Screen.HomeScreen)
                }
            }
            .addOnFailureListener {
                loginInProgress.value = false
                Log.d(TAG, "Inside_onFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

    private fun validateLoginUIDataWithRules() {
        val emailResult = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = loginUIState.value.password
        )

        loginValidationState.value = loginValidationState.value.copy(
            emailError = emailResult,
            passwordError = passwordResult
        )

        allValidationsPassed.value = emailResult && passwordResult
    }

}