package com.example.monumental.presentation.screens.main_screen

import android.os.Parcelable
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.monumental.R
import com.example.monumental.domain.model.BottomNavItem
import com.example.monumental.domain.model.Habit
import com.example.monumental.presentation.screens.add_habit.AddHabitScreen
import com.example.monumental.presentation.screens.community.CommunityScreen
import com.example.monumental.presentation.screens.courses.CoursesScreen
import com.example.monumental.presentation.screens.home.HomeScreen
import com.example.monumental.presentation.screens.nav_graph.Route
import com.example.monumental.presentation.screens.settings.SettingsScreen
import com.example.monumental.ui.theme.MonumentalTheme

@Composable
fun MainScreen() {
    val bottomNavItems = listOf(
        BottomNavItem(
            activeIcon = R.drawable.ic_home_active,
            inactiveIcon = R.drawable.ic_home_inactive,
            title = "Home"
        ),
        BottomNavItem(
            activeIcon = R.drawable.ic_courses_active,
            inactiveIcon = R.drawable.ic_courses_inactive,
            title = "Courses"
        ),
        BottomNavItem(
            activeIcon = R.drawable.ic_community_active,
            inactiveIcon = R.drawable.ic_community_inactive,
            title = "Community"
        ),
        BottomNavItem(
            activeIcon = R.drawable.ic_settings_active,
            inactiveIcon = R.drawable.ic_settings_inactive,
            title = "Settings"
        ),
    )

    var selectedIndex by remember {
        mutableIntStateOf(0)
    }
    val navController = rememberNavController()
    val bottomBarVisibleOnTabs = listOf(
        Route.HomeScreen.route,
        Route.CoursesScreen.route,
        Route.CommunityScreen.route,
        Route.SettingsScreen.route
    )
    val backStackState = navController.currentBackStackEntryAsState().value
    val isBottomBarVisible = remember(key1 = backStackState) {
        bottomBarVisibleOnTabs.contains(backStackState?.destination?.route)
    }
    val fabVisibleOnTabsExcept = listOf(
        Route.AddHabitScreen.route,
        Route.HabitDetailsScreen.route
    )
    val isFABVisible = remember(key1 = backStackState) {
        !(fabVisibleOnTabsExcept.contains(backStackState?.destination?.route))
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible)
                NavigationBar(
                    containerColor = colorResource(id = R.color.white),
                ) {
                    bottomNavItems.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = colorResource(id = R.color.white),
                            ),
                            onClick = {
                                selectedIndex = index
                                navigateToTab(navController, getTabRoute(index).route)
                            },
                            icon = {
                                val isSelected = selectedIndex == index
                                Image(
                                    painter = painterResource(id = item.activeIcon),
                                    contentDescription = null,
                                    colorFilter = if (!isSelected) ColorFilter.colorMatrix(
                                        ColorMatrix().apply { setToSaturation(0f) }) else null
                                )
                            }
                        )
                    }
                }
        },
        floatingActionButton = {
            if (isFABVisible)
                IconButton(
                    modifier = Modifier.border(
                        width = 6.dp,
                        color = colorResource(id = R.color.orange_20),
                        shape = CircleShape
                    ),
                    onClick = {
                        navController.navigate(Route.AddHabitScreen.route)
                    },
                    colors = IconButtonDefaults.filledTonalIconButtonColors(
                        containerColor = colorResource(id = R.color.orange)
                    ),
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = colorResource(id = R.color.eclipse)
                    )
                }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Tabs
            composable(
                Route.HomeScreen.route,
                enterTransition = null,
                exitTransition = null,
                popEnterTransition = null,
            ) {
                HomeScreen()
            }
            composable(
                Route.CoursesScreen.route,
                enterTransition = null,

                exitTransition = null,
                popEnterTransition = null,
            ) {
                CoursesScreen()
            }
            composable(Route.CommunityScreen.route) {
                CommunityScreen()
            }
            composable(Route.SettingsScreen.route) {
                SettingsScreen()
            }

            composable(Route.AddHabitScreen.route) {
                AddHabitScreen(
                    onBack = {
                        navController.popBackStack()
                    }
                )
            }
            composable(Route.HabitDetailsScreen.route) { backStackEntry ->
                val habit = backStackEntry.arguments?.getParcelable<Parcelable>("habit") as? Habit
            }
        }

    }
}

fun grayscaleColorMatrix(): FloatArray {
    return floatArrayOf(
        0.2126f, 0.7152f, 0.0722f, 0f, 0f,
        0.2126f, 0.7152f, 0.0722f, 0f, 0f,
        0.2126f, 0.7152f, 0.0722f, 0f, 0f,
        0f, 0f, 0f, 1f, 0f
    )
}

private fun getTabRoute(tabIndex: Int): Route {
    return when (tabIndex) {
        0 -> Route.HomeScreen
        1 -> Route.CoursesScreen
        2 -> Route.CommunityScreen
        3 -> Route.SettingsScreen
        else -> Route.HomeScreen
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Preview
@Composable
fun MainScreenPrev() {
    MonumentalTheme {
        MainScreen()
    }
}