package com.sodikdev.khatapps.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sodikdev.khatapps.ui.navigation.Screen
import com.sodikdev.khatapps.ui.screen.about.aboutScreenRoute
import com.sodikdev.khatapps.ui.screen.history.historyScreenRoute
import com.sodikdev.khatapps.ui.screen.home.homeScreenRoute
import com.sodikdev.khatapps.ui.screen.scan_result.scanResultScreenRoute
import com.sodikdev.khatapps.ui.screen.splash.splashScreenRoute

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier,
    ) {
        splashScreenRoute(navController)
        homeScreenRoute(navController)
        historyScreenRoute(navController)
        aboutScreenRoute(navController)

        scanResultScreenRoute(navController)
    }
}