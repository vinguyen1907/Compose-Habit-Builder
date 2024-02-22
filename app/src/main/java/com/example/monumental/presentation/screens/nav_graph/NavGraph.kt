package com.example.monumental.presentation.screens.nav_graph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.monumental.presentation.screens.forgot_password.ForgotPasswordScreen
import com.example.monumental.presentation.screens.forgot_password.ForgotPasswordViewModel
import com.example.monumental.presentation.screens.main_screen.MainScreen
import com.example.monumental.presentation.screens.onboarding.OnboardingScreen
import com.example.monumental.presentation.screens.onboarding.OnboardingViewModel
import com.example.monumental.presentation.screens.sign_in.SignInScreen
import com.example.monumental.presentation.screens.sign_in.SignInViewModel
import com.example.monumental.presentation.screens.sign_up.SignUpScreen
import com.example.monumental.presentation.screens.sign_up.SignUpViewModel

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.StartingNavigation.route,
            startDestination = Route.OnboardingScreen.route,
        ) {
            composable(
                route = Route.OnboardingScreen.route,
            ) {
                val viewModel: OnboardingViewModel = hiltViewModel()
                OnboardingScreen(onEvent = viewModel::onEvent)
            }
        }
        navigation(
            route = Route.AuthenticationNavigation.route,
            startDestination = Route.MainNavigation.route,
        ) {
            composable(
                route = Route.SignInScreen.route,
            ) {
                val viewModel: SignInViewModel = hiltViewModel()
                SignInScreen(
                    viewModel = viewModel,
                    navigate = { route -> navController.navigate(route) }
                )
            }
            composable(
                route = Route.SignUpScreen.route,
            ) {
                val viewModel: SignUpViewModel = hiltViewModel()
                SignUpScreen(
                    viewModel = viewModel,
                    navigate = { route -> navController.navigate(route) }
                )
            }
            composable(
                route = Route.ForgotPassword.route,
            ) {
                val viewModel: ForgotPasswordViewModel = hiltViewModel()
                ForgotPasswordScreen(
                    viewModel = viewModel,
                    navigate = { route -> navController.navigate(route) }
                )
            }
            composable(
                route = Route.MainNavigation.route,
            ) {
                MainScreen()
            }
        }
    }
}