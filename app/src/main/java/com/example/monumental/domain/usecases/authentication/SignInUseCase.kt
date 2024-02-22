package com.example.monumental.domain.usecases.authentication

import com.example.monumental.domain.manager.AuthenticationManager

class SignInUseCase(
    private val authenticationManager: AuthenticationManager
) {

    operator fun invoke(email: String, password: String, onResult: (Throwable?) -> Unit) {
        authenticationManager.authenticate(email, password) {
            onResult(it)
        }
    }
}