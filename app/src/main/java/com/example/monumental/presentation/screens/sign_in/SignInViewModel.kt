package com.example.monumental.presentation.screens.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.monumental.constants.enums.LoginStatus
import com.example.monumental.domain.usecases.authentication.AuthenticationUseCases
import com.example.monumental.presentation.validators.AuthenticationValidator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationUseCases: AuthenticationUseCases,
) : ViewModel() {
    var state by mutableStateOf(SignInUiState())
        private set

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                state = state.copy(
                    email = event.email
                )
            }

            is SignInEvent.PasswordChanged -> {
                state = state.copy(
                    password = event.password
                )
            }
            is SignInEvent.Validate -> {
                validate()
            }

            is SignInEvent.SignInWithEmailAndPassword -> {
                if (validate()) {
                    state = state.copy(
                        status = LoginStatus.LOADING,
                    )
                    authenticationUseCases.signInWithEmailPassword(
                        email = state.email,
                        password = state.password,
                        onResult = {exception ->
                            if (exception == null) {
                                state = state.copy(
                                    status = LoginStatus.SUCCESS,
                                )
                            } else {
                                state = state.copy(
                                    status = LoginStatus.ERROR,
                                    error = exception.message ?: "An error occurred",
                                )
//                                if (exception is FirebaseAuthException) {
//                                    if (exception.errorCode == )
//                                }
                            }
                        }
                    )
                }
            }

            is SignInEvent.CreateAnonymousAccount -> {
                authenticationUseCases.createAnonymousAccount() {exception ->
                    // TODO: Handle exception
                }
            }

            is SignInEvent.ResetStatus -> {
                state = state.copy(
                    status = LoginStatus.NONE,
                    error = ""
                )
            }
        }
    }

    private fun validate(): Boolean {
        state = state.copy(
            emailValidation = AuthenticationValidator.validateEmail(state.email),
            passwordValidation = AuthenticationValidator.validatePasswordEmpty(state.password)
        )
        return state.emailValidation.isValid && state.passwordValidation.isValid
    }
}