package com.example.monumental.presentation.screens.sign_up

sealed class SignUpEvent {
    // UI logic
    data class NameChanged(val name: String) : SignUpEvent()
    data class EmailChanged(val email: String) : SignUpEvent()
    data class PasswordChanged(val password: String) : SignUpEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()
    data class KeepSignedInChanged(val keepSignedIn: Boolean) : SignUpEvent()
    data class SubscribeToNewsletterChanged(val subscribeToNewsletter: Boolean) : SignUpEvent()

    // Business logic
    data object ResetStatus : SignUpEvent()
    data object SignUp : SignUpEvent()
}