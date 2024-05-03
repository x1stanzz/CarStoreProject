package com.example.carstoreproject.data.rules

data class ValidatorState(
    var firstNameError: Boolean = false,
    var lastNameError: Boolean = false,
    var emailError: Boolean = false,
    var passwordError: Boolean = false,
    var confirmPasswordError: Boolean = false
)