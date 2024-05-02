package com.example.carstoreproject.data

sealed class UIEvent {
    data class FirstNameChanged(val firstName: String) : UIEvent()
    data class LastNameChanged(val lastName: String) : UIEvent()
    data class EmailChanged(val email: String) : UIEvent()
    data class PasswordChanged(val password: String) : UIEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : UIEvent()
    object RegisterButtonClicked : UIEvent()

}