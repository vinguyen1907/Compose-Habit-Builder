package com.example.monumental.presentation.screens.sign_up

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monumental.R
import com.example.monumental.constants.enums.SignUpStatus
import com.example.monumental.presentation.common.CustomDialog
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.presentation.common.PasswordInput
import com.example.monumental.presentation.common.TextInput
import com.example.monumental.presentation.common.TextWithAction
import com.example.monumental.presentation.screens.nav_graph.Route
import com.example.monumental.presentation.screens.sign_in.components.LoadingScreen
import com.example.monumental.presentation.screens.sign_in.components.SocialButton
import com.example.monumental.presentation.screens.sign_up.components.CheckboxTile
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding

@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
) {
    val state = viewModel.state
    val onEvent = viewModel::onEvent

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_bg_color))
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(0.45f),
            painter = painterResource(id = R.drawable.img_sign_up),
            contentDescription = null
        )
        Text(
            text = "CREATE YOUR ACCOUNT",
            style = MaterialTheme.typography.headlineLarge.copy(color = colorResource(id = R.color.eclipse))
        )
        Spacer(modifier = Modifier.size(32.dp))
        TextInput(
            value = state.name,
            onValueChange = { onEvent(SignUpEvent.NameChanged(it)) },
            containerColor = colorResource(id = R.color.white),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = "Name",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                        fontWeight = FontWeight.Medium,
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PageHorizontalPadding),
            isError = !state.nameValidation.isValid,
            errorText = state.nameValidation.message,
        )
        Spacer(modifier = Modifier.size(8.dp))
        TextInput(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.EmailChanged(it)) },
            containerColor = colorResource(id = R.color.white),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_email),
                    contentDescription = null,
                )
            },
            keyboardType = KeyboardType.Email,
            placeholder = {
                Text(
                    text = "Email",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                        fontWeight = FontWeight.Medium,
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PageHorizontalPadding),
            isError = !state.emailValidation.isValid,
            errorText = state.emailValidation.message,
        )
        Spacer(modifier = Modifier.size(8.dp))
        PasswordInput(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.PasswordChanged(it)) },
            containerColor = colorResource(id = R.color.white),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = "Password",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                        fontWeight = FontWeight.Medium,
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PageHorizontalPadding),
            isError = !state.passwordValidation.isValid,
            errorText = state.passwordValidation.message,
        )
        Spacer(modifier = Modifier.size(8.dp))
        PasswordInput(
            value = state.confirmPassword,
            onValueChange = { onEvent(SignUpEvent.ConfirmPasswordChanged(it)) },
            containerColor = colorResource(id = R.color.white),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_password),
                    contentDescription = null,
                )
            },
            placeholder = {
                Text(
                    text = "Confirm Password",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                        fontWeight = FontWeight.Medium,
                    )
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PageHorizontalPadding),
            isError = !state.confirmPasswordValidation.isValid,
            errorText = state.confirmPasswordValidation.message,
        )
        CheckboxTile(
            modifier = Modifier.padding(top = 28.dp),
            checked = state.keepSignedIn,
            onCheckedChange = { onEvent(SignUpEvent.KeepSignedInChanged(it)) },
            title = "Keep me signed in"
        )
        Spacer(modifier = Modifier.size(18.dp))
        CheckboxTile(
            modifier = Modifier.padding(bottom = 8.dp),
            checked = state.subscribeToNewsletter,
            onCheckedChange = { onEvent(SignUpEvent.SubscribeToNewsletterChanged(it)) },
            title = "Email me about special pricing and more"
        )
        CustomFilledTonalButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            onClick = {
                onEvent(SignUpEvent.SignUp)
            },
            text = "Sign Up"
        )
        Row(
            modifier = Modifier.padding(horizontal = PageHorizontalPadding),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 8.dp),
                color = colorResource(id = R.color.eclipse).copy(alpha = 0.2f),
            )
            Text(
                text = "Or Sign in with",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                )
            )
            Divider(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                color = colorResource(id = R.color.eclipse).copy(alpha = 0.2f),
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = PageHorizontalPadding),
        ) {
            SocialButton(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_google, text = "Google"
            )
            Spacer(modifier = Modifier.width(12.dp))
            SocialButton(
                modifier = Modifier.weight(1f),
                icon = R.drawable.ic_facebook, text = "Facebook"
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        TextWithAction(label = "Already have an account?", actionText = "Sign in",
            onAction = {
                navigate(Route.SignInScreen.route)
            }
        )
    }

    if (state.status == SignUpStatus.SUCCESS) {
        CustomDialog(
            title = "SUCCESS",
            content = "You have successfully created your account",
            confirmText = "Let Started", onConfirm = {
                onEvent(SignUpEvent.ResetStatus)
                // TODO:
            })
    } else if (state.status == SignUpStatus.ERROR) {
        if (state.error.isNotBlank()) {
            CustomDialog(
                title = "SIGN UP FAILED",
                content = state.error,
                confirmText = "Try again",
                onConfirm = {
                    onEvent(SignUpEvent.ResetStatus)
                },
            )
        }
    }

    if (state.status == SignUpStatus.LOADING)
        LoadingScreen()
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPrev() {
    MonumentalTheme {
        SignUpScreen(
            navigate = {}
        )
    }
}