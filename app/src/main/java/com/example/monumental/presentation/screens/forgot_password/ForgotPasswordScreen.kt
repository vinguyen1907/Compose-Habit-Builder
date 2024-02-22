package com.example.monumental.presentation.screens.forgot_password

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.monumental.R
import com.example.monumental.presentation.common.CustomFilledTonalButton
import com.example.monumental.presentation.common.DefaultAppBarLeading
import com.example.monumental.presentation.common.TextInput
import com.example.monumental.presentation.common.TextWithAction
import com.example.monumental.presentation.screens.nav_graph.Route
import com.example.monumental.ui.theme.MonumentalTheme
import com.example.monumental.utils.Dimens.PageHorizontalPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(
    viewModel: ForgotPasswordViewModel = hiltViewModel(),
    navigate: (String) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                navigationIcon = {
                    DefaultAppBarLeading {
                        navigate(Route.SignInScreen.route)
                    }
                    IconButton(
                        content = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_arrow_left),
                                contentDescription = "Back button"
                            )
                        },
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledTonalIconButtonColors(
                            containerColor = colorResource(id = R.color.eclipse_10),
                            contentColor = colorResource(id = R.color.eclipse),
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_bg_color),
                ),
            )
        },
        containerColor = colorResource(id = R.color.primary_bg_color)
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(top = 27.dp, start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                "FORGOT YOUR PASSWORD",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.size(38.dp))
            Image(
                painter = painterResource(id = R.drawable.img_forgot_password),
                contentDescription = null
            )

            Spacer(modifier = Modifier.height(45.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(colorResource(id = R.color.white))
                    .padding(PageHorizontalPadding)
            ) {
                Column() {
                    Text(
                        "Enter your registered email below to receive password reset instruction",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Medium),
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.size(30.dp))
                    TextInput(
                        modifier = Modifier.fillMaxWidth(),
                        value = viewModel.email,
                        onValueChange = {},
                        placeholder = {
                            Text(
                                text = "Email",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    color = colorResource(id = R.color.eclipse).copy(alpha = 0.5f),
                                    fontWeight = FontWeight.Medium,
                                )
                            )
                        },

                    )
                    Spacer(modifier = Modifier.size(10.dp))
                    CustomFilledTonalButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { /*TODO*/ }
                    ) {
                        Text(
                            text = "Send reset email",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.fillMaxHeight(fraction = 0.5f))

            TextWithAction(
                label = "Remember your password?",
                actionText = "Log in",
                onAction = {
                    navigate(Route.SignInScreen.route)
                }
            )
        }
    }
}

@Preview
@Composable
fun ForgotPasswordPrev() {
    MonumentalTheme {
        ForgotPasswordScreen(
            navigate = {}
        )
    }
}