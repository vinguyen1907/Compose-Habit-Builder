package com.example.monumental.domain.usecases.authentication

data class AuthenticationUseCases(
    val createAnonymousAccount: CreateAnonymousAccountUseCase,
    val signInWithEmailPassword: SignInUseCase,
    val linkAccount: LinkAccountUseCase,
    val createAccount: CreateAccountUseCase,
    val validate: ValidateUseCase,
)