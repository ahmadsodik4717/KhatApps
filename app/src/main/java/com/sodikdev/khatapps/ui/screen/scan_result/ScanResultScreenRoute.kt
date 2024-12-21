package com.sodikdev.khatapps.ui.screen.scan_result

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sodikdev.khatapps.ui.navigation.Screen

fun NavGraphBuilder.scanResultScreenRoute(navController: NavController) {
    composable(route = Screen.ScanResult.route) {
        val imageUri = navController.previousBackStackEntry?.savedStateHandle?.get<Uri>("image_uri")
        if(imageUri != null) {
            ScanResultScreen(
                navController = navController,
                imageUri = imageUri
            )
        }
    }
}