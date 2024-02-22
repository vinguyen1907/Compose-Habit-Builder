package com.example.monumental.presentation.validators

data class ValidationResult(
    val isValid: Boolean,
    val message: String? = null,
)