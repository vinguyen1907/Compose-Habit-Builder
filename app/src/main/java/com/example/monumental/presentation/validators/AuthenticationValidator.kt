package com.example.monumental.presentation.validators

import com.example.monumental.presentation.validators.ValidationMessage.EMPTY_EMAIL_MSG
import com.example.monumental.presentation.validators.ValidationMessage.EMPTY_PASSWORD_MSG
import com.example.monumental.presentation.validators.ValidationMessage.INVALID_EMAIL_MSG
import com.example.monumental.presentation.validators.ValidationMessage.INVALID_PASSWORD_MSG

class AuthenticationValidator {
    companion object {
        fun validateEmail(email: String): ValidationResult {
            if (email.isEmpty()) {
                return ValidationResult(
                    isValid = false,
                    message = EMPTY_EMAIL_MSG
                )
            }
            val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
            if (!emailPattern.matches(email)) {
                return ValidationResult(
                    isValid = false,
                    message = INVALID_EMAIL_MSG
                )
            }
            return ValidationResult(
                isValid = true
            )
        }

        fun validatePassword(password: String): ValidationResult {
            if (password.isEmpty()) {
                return ValidationResult(
                    isValid = false,
                    message = EMPTY_PASSWORD_MSG
                )
            }
            val passwordPattern = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}\$")
            if (!passwordPattern.matches(password)) {
                println("Not Matches")
                return ValidationResult(
                    isValid = false,
                    message = INVALID_PASSWORD_MSG
                )
            }
            return ValidationResult(
                isValid = true
            )
        }

        fun validatePasswordEmpty(password: String): ValidationResult {
            if (password.isEmpty()) {
                return ValidationResult(
                    isValid = false,
                    message = EMPTY_PASSWORD_MSG
                )
            }
            return ValidationResult(
                isValid = true
            )
        }

        fun validateConfirmationPassword(password: String, confirmPassword: String): ValidationResult {
            if (password != confirmPassword) {
                return ValidationResult(
                    isValid = false,
                    message = "Passwords do not match"
                )
            }
            return ValidationResult(
                isValid = true
            )
        }

        fun validateName(name: String): ValidationResult {
            if (name.isEmpty()) {
                return ValidationResult(
                    isValid = false,
                    message = "Name cannot be empty"
                )
            }
            return ValidationResult(
                isValid = true
            )
        }
    }

}