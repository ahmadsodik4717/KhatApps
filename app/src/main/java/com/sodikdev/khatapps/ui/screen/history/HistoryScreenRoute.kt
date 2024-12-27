package com.sodikdev.khatapps.ui.screen.history

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sodikdev.khatapps.ui.navigation.Screen

fun NavGraphBuilder.historyScreenRoute(navController: NavController) {
    composable(route = Screen.History.route) {
        HistoryScreen(navController = navController)
    }
}