package com.example.monumental.presentation.screens.sign_in

sealed class SignInEvent {
    data class EmailChanged(val email: String) : SignInEvent()
    data class PasswordChanged(val password: String) : SignInEvent()
    data object Validate: SignInEvent()
    data object SignInWithEmailAndPassword : SignInEvent()
    data object CreateAnonymousAccount: SignInEvent()
    data object ResetStatus: SignInEvent()
}