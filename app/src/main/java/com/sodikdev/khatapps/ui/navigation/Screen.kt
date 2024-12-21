package com.sodikdev.khatapps.ui.navigation

sealed class Screen(val route: String) {

    object Splash: Screen("splash")

    object Home: Screen("home")
    object History: Screen("history")
    object About: Screen("about")

    object Scan: Screen("scan")
    object ScanResult: Screen("scan_result")
}