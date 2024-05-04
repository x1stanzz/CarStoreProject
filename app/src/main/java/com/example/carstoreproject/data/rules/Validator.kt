package com.example.carstoreproject.data.rules

object Validator {
    fun validateFirstName(fName: String): Boolean {
        return fName.isNotEmpty() && fName.length >= 2

    }
    fun validateLastName(lName: String): Boolean {
        return lName.isNotEmpty() && lName.length >= 2

    }
    fun validateEmail(email: String): Boolean {
        return email.isNotEmpty() && isEmailValid(email)

    }
    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty() && password.length >= 8

    }
    fun validateConfirmPassword(
        password: String,
        confirmPassword: String
    ): Boolean {
        return confirmPassword.isNotEmpty() && confirmPassword == password
    }

    fun validatePrivacyPolicy(
        privacyPolicy: Boolean
    ): Boolean {
        return privacyPolicy
    }

    private fun isEmailValid(email: String): Boolean {
        val emailRegex = Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        return emailRegex.matches(email)
    }
}

data class ValidationResult(
    val status: Boolean
)