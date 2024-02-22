package com.example.monumental.presentation.screens.nav_graph

sealed class Route (
    val route: String,
) {
    data object StartingNavigation: Route("starting_navigation")
    data object OnboardingScreen: Route("onboarding_screen")
    data object AuthenticationNavigation: Route("authentication_navigation")
    data object SignInScreen: Route("sign_in_screen")
    data object SignUpScreen: Route("sign_up_screen")
    data object ForgotPassword: Route("forgot_password")

    // Main navigation
    data object MainNavigation: Route("main_navigation")
    data object HomeScreen: Route("home_screen")
    data object CoursesScreen: Route("courses_screen")
    data object CommunityScreen: Route("community_screen")
    data object SettingsScreen: Route("settings_screen")
    data object AddHabitScreen: Route("add_habit_screen")
    data object HabitDetailsScreen: Route("habit_details_screen")
}