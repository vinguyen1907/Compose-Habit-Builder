package com.example.monumental.presentation.screens.sign_in

import com.example.monumental.constants.enums.LoginStatus
import com.example.monumental.presentation.validators.ValidationResult

data class SignInUiState(
    val email: String = "",
    val password: String = "",
    val emailValidation: ValidationResult = ValidationResult(isValid = true),
    val passwordValidation: ValidationResult = ValidationResult(isValid = true),
    val status: LoginStatus = LoginStatus.NONE,
    val error: String = ""
)