package com.example.monumental.domain.usecases.authentication

import com.example.monumental.domain.manager.AuthenticationManager

class LinkAccountUseCase(
    private val authenticationManager: AuthenticationManager
) {

        operator fun invoke(email: String, password: String) {
            authenticationManager.linkAccount(email, password) {
                // TODO: Handle result
            }
        }
}