package com.example.monumental.presentation.screens.sign_up

import com.example.monumental.constants.enums.SignUpStatus
import com.example.monumental.presentation.validators.ValidationResult

data class SignUpState (
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val keepSignedIn: Boolean = true,
    val subscribeToNewsletter: Boolean = true,
    val nameValidation: ValidationResult = ValidationResult(isValid = true),
    val emailValidation: ValidationResult = ValidationResult(isValid = true),
    val passwordValidation: ValidationResult = ValidationResult(isValid = true),
    val confirmPasswordValidation: ValidationResult = ValidationResult(isValid = true),
    val status: SignUpStatus = SignUpStatus.NONE,
    val error: String = ""
)