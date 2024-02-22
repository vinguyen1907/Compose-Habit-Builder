package com.example.monumental.domain.usecases.authentication

import com.example.monumental.domain.manager.AuthenticationManager

class CreateAnonymousAccountUseCase(
    private val authenticationManager: AuthenticationManager,
) {

    operator fun invoke(onResult: (Throwable?) -> Unit) {
        authenticationManager.createAnonymousAccount(onResult = {
            onResult(it)
        })
    }
}