package com.example.monumental.presentation.screens.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.monumental.constants.enums.SignUpStatus
import com.example.monumental.domain.usecases.authentication.AuthenticationUseCases
import com.example.monumental.presentation.validators.AuthenticationValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
) : ViewModel() {
    var state by mutableStateOf(SignUpState())
        private set

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.NameChanged -> {
                state = state.copy(name = event.name)
            }

            is SignUpEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is SignUpEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is SignUpEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword)
            }

            is SignUpEvent.KeepSignedInChanged -> {
                state = state.copy(keepSignedIn = event.keepSignedIn)
            }

            is SignUpEvent.SubscribeToNewsletterChanged -> {
                state = state.copy(subscribeToNewsletter = event.subscribeToNewsletter)
            }

            is SignUpEvent.ResetStatus -> {
                state = state.copy(
                    status = SignUpStatus.NONE,
                    error = "",
                )
            }

            is SignUpEvent.SignUp -> {
                if (!validate()) {
                    return
                }

                state = state.copy(
                    status = SignUpStatus.LOADING,
                )
                authenticationUseCases.createAccount(
                    email = state.email,
                    password = state.password,
                    onResult = { exception ->
                        if (exception == null) {
                            state = state.copy(
                                status = SignUpStatus.SUCCESS,
                            )
                        } else {
                            state = state.copy(
                                error = exception.message ?: "An error occurred",
                                status = SignUpStatus.ERROR,
                            )
                        }

                    }
                )
            }
        }
    }

    private fun validate(): Boolean {
        val isNameValid  = AuthenticationValidator.validateName(state.name)
        val isEmailValid = AuthenticationValidator.validateEmail(state.email)
        val isPasswordValid = AuthenticationValidator.validatePassword(state.password)
        val isConfirmPasswordValid = AuthenticationValidator.validateConfirmationPassword(state.password, state.confirmPassword)
        state = state.copy(
            nameValidation = isNameValid,
            emailValidation = isEmailValid,
            passwordValidation = isPasswordValid,
            confirmPasswordValidation = isConfirmPasswordValid
        )
        return isNameValid.isValid && isEmailValid.isValid && isPasswordValid.isValid && isConfirmPasswordValid.isValid
    }
}