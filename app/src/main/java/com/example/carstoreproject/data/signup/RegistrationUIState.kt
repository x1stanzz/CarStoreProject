package com.example.carstoreproject.data.signup

data class RegistrationUIState(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var confirmPassword: String = "",
    var privacyPolicyAccepted: Boolean = false
)