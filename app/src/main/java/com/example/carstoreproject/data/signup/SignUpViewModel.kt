package com.example.carstoreproject.data.signup

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.carstoreproject.data.rules.SignUpValidationState
import com.example.carstoreproject.data.rules.Validator
import com.example.carstoreproject.navigation.AcceleratoRouter
import com.example.carstoreproject.navigation.AuthScreen
import com.google.firebase.auth.FirebaseAuth

class SignUpViewModel : ViewModel() {
    private val TAG = SignUpViewModel::class.simpleName

    var registrationUIState = mutableStateOf(RegistrationUIState())

    val signUpValidationState = mutableStateOf(SignUpValidationState())

    var allValidationPassed = mutableStateOf(false)

    var signUpInProgress = mutableStateOf(false)
    fun onEvent(event: SignUpUIEvent) {
        when(event) {
            is SignUpUIEvent.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is SignUpUIEvent.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is SignUpUIEvent.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignUpUIEvent.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignUpUIEvent.ConfirmPasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    confirmPassword = event.confirmPassword
                )
                printState()

            }
            is SignUpUIEvent.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
            is SignUpUIEvent.RegisterButtonClicked -> {
                signUp()
            }
        }
    }
    private fun signUp() {
        Log.d(TAG, "Inside_signUp")
        printState()
        validateDataWithRules()
        if(allValidationPassed.value)
            createUserInFirebase(
                email = registrationUIState.value.email,
                password = registrationUIState.value.password
            )
    }
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, registrationUIState.value.toString())
        Log.d(TAG, signUpValidationState.value.toString())
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

        var privacyPolicyResult = Validator.validatePrivacyPolicy(
            privacyPolicy = registrationUIState.value.privacyPolicyAccepted
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult=$fNameResult")
        Log.d(TAG, "lNameResult=$lNameResult")
        Log.d(TAG, "emailResult=$emailResult")
        Log.d(TAG, "passwordResult=$passwordResult")
        Log.d(TAG, "confirmPasswordResult=$confirmPasswordResult")
        Log.d(TAG, "privacyPolicyResult=$privacyPolicyResult")

        signUpValidationState.value = signUpValidationState.value.copy(
            firstNameError = fNameResult,
            lastNameError = lNameResult,
            emailError = emailResult,
            passwordError = passwordResult,
            confirmPasswordError = confirmPasswordResult,
            privacyPolicyError = privacyPolicyResult
        )

        allValidationPassed.value = fNameResult && lNameResult && emailResult
                && passwordResult && confirmPasswordResult && privacyPolicyResult
    }

    private fun createUserInFirebase(email: String, password: String) {
        signUpInProgress.value = true
        FirebaseAuth
            .getInstance()
            .createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                Log.d(TAG, "Inside_onCompleteListener")
                Log.d(TAG, "isSuccessful = ${it.isSuccessful}")

                signUpInProgress.value = false

                if(it.isSuccessful) {
                    AcceleratoRouter.navigateTo(AuthScreen.MainAuthScreen)
                }
            }
            .addOnFailureListener {
                signUpInProgress.value = false
                Log.d(TAG, "Inside_onFailureListener")
                Log.d(TAG, "Exception = ${it.message}")
                Log.d(TAG, "Exception = ${it.localizedMessage}")
            }
    }

}