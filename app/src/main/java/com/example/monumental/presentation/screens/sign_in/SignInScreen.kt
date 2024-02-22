package com.example.monumental.presentation.screens.sign_in

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monumental.R
import com.example.monumental.constants.enums.LoginStatus
import com.example.monumental.presentation.common.CustomDialog
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.presentation.common.PasswordInput
import com.example.monumental.presentation.common.TextInput
import com.example.monumental.presentation.common.TextWithAction
import com.example.monumental.presentation.screens.nav_graph.Route
import com.example.monumental.presentation.screens.sign_in.components.LoadingScreen
import com.example.monumental.presentation.screens.sign_in.components.SocialButton
import com.example.monumental.presentation.validators.ValidationResult
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
) {
    val state = viewModel.state
    val onEvent = viewModel::onEvent

    SignInScreen(
        email = state.email,
        password = state.password,
        emailValidation = state.emailValidation,
        passwordValidation = state.passwordValidation,
        error = state.error,
        status = state.status,
        onEvent = onEvent,
        navigate = navigate,
    )
}

@Composable
fun SignInScreen(
    email: String,
    password: String,
    emailValidation: ValidationResult,
    passwordValidation: ValidationResult,
    error: String,
    status: LoginStatus,
    onEvent: (SignInEvent) -> Unit,
    navigate: (String) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(0.77f)
            .background(colorResource(id = R.color.primary_bg_color))
            .paint(
                painter = painterResource(id = R.drawable.img_sign_in_background),
                contentScale = ContentScale.FillWidth,
                alignment = Alignment.TopCenter
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "WELCOME TO Monumental habits",
                style = MaterialTheme.typography.displaySmall.copy(color = colorResource(id = R.color.eclipse)),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(40.dp))
            SocialButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                icon = R.drawable.ic_google,
                text = "Sign in with Google"
            )
            SocialButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp,
                        vertical = 12.dp
                    ),
                icon = R.drawable.ic_facebook,
                text = "Sign in with Facebook"
            )
            Box(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    )
                    .background(colorResource(id = R.color.white))
                    .padding(vertical = 12.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        "Log in with Email",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Medium,
                            color = colorResource(id = R.color.eclipse)
                        ),
                    )
                    Divider(
                        modifier = Modifier.padding(top = 12.dp, bottom = 16.dp),
                        color = colorResource(id = R.color.primary_bg_color),
                    )
                    TextInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = email,
                        onValueChange = { value -> onEvent(SignInEvent.EmailChanged(value)) },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = "Email",
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                "Email",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                                    fontWeight = FontWeight.Medium,
                                )
                            )
                        },
                        keyboardType = KeyboardType.Email,
                        isError = !emailValidation.isValid,
                        errorText = emailValidation.message,
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    PasswordInput(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        value = password,
                        onValueChange = { value ->
                            onEvent(
                                SignInEvent.PasswordChanged(
                                    value
                                )
                            )
                        },
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "Email",
                                modifier = Modifier.size(16.dp)
                            )
                        },
                        placeholder = {
                            Text(
                                "Password",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                                    fontWeight = FontWeight.Medium,
                                )
                            )
                        },
                        isError = !passwordValidation.isValid,
                        errorText = passwordValidation.message,
                    )
                    CustomFilledTonalButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        onClick = {
                            onEvent(SignInEvent.SignInWithEmailAndPassword)
                        }) {
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.titleMedium.copy(
                                color = colorResource(id = R.color.eclipse),
                            )
                        )
                    }
                    Text(
                        text = "Forgot password?",
                        style = MaterialTheme.typography.titleSmall.copy(
                            color = colorResource(id = R.color.eclipse),
                            fontWeight = FontWeight.Medium,
                        ),
                        modifier = Modifier.clickable {
                            /*TODO*/
                        }
                    )
                    Spacer(modifier = Modifier.height(6.dp))

                    TextWithAction(
                        label = "Don't have an account?",
                        actionText = "Sign up",
                        onAction = {
                            navigate(Route.SignUpScreen.route)
                        }
                    )
                }
            }
        }
    }

    if (status == LoginStatus.SUCCESS) {
        CustomDialog(
            title = "SUCCESS",
            content = "You have successfully logged in",
            confirmText = "OK", onConfirm = {
                onEvent(SignInEvent.ResetStatus)
            })
    } else if (status == LoginStatus.ERROR) {
        if (error.isNotBlank()) {
            CustomDialog(
                title = "LOGIN FAILED",
                content = error,
                confirmText = "OK",
                onConfirm = {
                    onEvent(SignInEvent.ResetStatus)
                },
            )
        }
    }

    if (status == LoginStatus.LOADING)
        LoadingScreen()
}

@Preview(device = "id:pixel_5")
@Composable
fun SignScreenPrev() {
    MonumentalTheme {
        SignInScreen(
            email = "",
            password = "",
            emailValidation = ValidationResult(true, ""),
            passwordValidation = ValidationResult(true, ""),
            error = "",
            status = LoginStatus.NONE,
            onEvent = {},
            navigate = {},
        )
    }
}