package com.example.jobboard.utils

import android.util.Patterns

fun validateEmail(email: String): AuthError {
    if(email.isEmpty()) {
        return AuthError.EmptyEmail
    } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        return AuthError.WrongEmail
    }
    return AuthError.NoErrors
}

fun validatePassword(password: String): AuthError {
    val pattern = Regex("^(?=.*[0-9])(?=.*[\\p{Ll}])(?=.*[\\p{Lu}])(?=\\S+\$).{8,}\$")
    if(password.isEmpty()) {
        return AuthError.EmptyPassword
    } else if (password.length < 8) {
        return AuthError.LessThan8Characters
    } else if (!password.matches(pattern)) {
        return AuthError.WrongPassword
    }
    return AuthError.NoErrors
}

sealed class AuthError {
    object NoErrors : AuthError()

    object EmptyEmail : AuthError()
    object WrongEmail : AuthError()

    object EmptyPassword : AuthError()
    object LessThan8Characters : AuthError()
    object WrongPassword : AuthError()
}