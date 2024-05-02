package com.example.carstoreproject.data

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {
    private val TAG = LoginViewModel::class.simpleName
    private val _registrationUIState = MutableStateFlow(RegistrationUIState())
    val registrationUIState: StateFlow<RegistrationUIState> = _registrationUIState.asStateFlow()

    fun onEvent(event: UIEvent) {
        when(event) {
            is UIEvent.FirstNameChanged -> {
                _registrationUIState.update {currentState ->
                    currentState.copy(
                        firstName = event.firstName
                    )
                }
                printState()
            }
            is UIEvent.LastNameChanged -> {
                _registrationUIState.update { currentState ->
                    currentState.copy(
                        lastName = event.lastName
                    )
                }
                printState()

            }
            is UIEvent.EmailChanged -> {
                _registrationUIState.update { currentState ->
                    currentState.copy(
                        email = event.email
                    )
                }
                printState()
            }
            is UIEvent.PasswordChanged -> {
                _registrationUIState.update {currentState ->
                    currentState.copy(
                        password = event.password
                    )
                }
                printState()
            }
            is UIEvent.ConfirmPasswordChanged -> {
                _registrationUIState.update { currentState ->
                    currentState.copy(
                        confirmPassword = event.confirmPassword
                    )
                }
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
    }
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
    }

//    private fun checkPasswordEquality() {
//        Log.d(TAG, "${registrationUIState.value.password == registrationUIState.value.confirmPassword}")
//    }
}