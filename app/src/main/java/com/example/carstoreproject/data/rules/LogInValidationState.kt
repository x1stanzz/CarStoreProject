package com.example.carstoreproject.data.rules

data class LogInValidationState (
    var emailError: Boolean = false,
    var passwordError: Boolean = false
)