package com.example.carstoreproject.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.data.rules.Validator
import com.example.carstoreproject.data.rules.ValidatorState

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    val validatorState = mutableStateOf(ValidatorState())
    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is UIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()

            }
            is UIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is UIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is UIEvent.ConfirmPasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
                printState()

            }
            is UIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }
    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        validateDataWithRules()
    }
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
        Log.d(TAG, validatorState.value.toString())
    }
    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = registrationUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = registrationUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = registrationUIState.value.password
        )
        val confirmPasswordResult = Validator.validateConfirmPassword(
            password = registrationUIState.value.password,
            confirmPassword = registrationUIState.value.confirmPassword
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult=$fNameResult")
        Log.d(TAG, "lNameResult=$lNameResult")
        Log.d(TAG, "emailResult=$emailResult")
        Log.d(TAG, "passwordResult=$passwordResult")
        Log.d(TAG, "confirmPasswordResult=$confirmPasswordResult")

        validatorState.value = validatorState.value.copy(
            firstNameError = fNameResult,
            lastNameError = lNameResult,
            emailError = emailResult,
            passwordError = passwordResult,
            confirmPasswordError = confirmPasswordResult
        )
    }

}