package com.example.monumental.domain.usecases.authentication

import com.example.monumental.domain.manager.AuthenticationManager

class CreateAccountUseCase(
    private val authenticationManager: AuthenticationManager,
) {

    operator fun invoke(email: String, password: String, onResult: (Throwable?) -> Unit) {
        authenticationManager.createAccount(email, password) {
            onResult(it)
        }
    }
}