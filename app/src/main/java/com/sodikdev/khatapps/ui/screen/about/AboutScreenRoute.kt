package com.sodikdev.khatapps.ui.screen.about

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sodikdev.khatapps.ui.navigation.Screen

fun NavGraphBuilder.aboutScreenRoute(navController: NavController) {
    composable(route = Screen.About.route) {
        AboutScreen(navController)
    }
}