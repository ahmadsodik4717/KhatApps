package com.sodikdev.khatapps.ui.screen.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sodikdev.khatapps.ui.navigation.Screen

fun NavGraphBuilder.splashScreenRoute(navController: NavController) {
    composable(
        route = Screen.Splash.route,
    ) {
        SplashScreen(
            onTimeOut = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}