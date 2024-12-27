package com.sodikdev.khatapps.ui.screen.scan_result

import android.net.Uri
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.sodikdev.khatapps.data.model.Khat
import com.sodikdev.khatapps.ui.navigation.Screen
import com.sodikdev.khatapps.util.DetectResult

fun NavGraphBuilder.scanResultScreenRoute(navController: NavController) {
    composable(route = Screen.ScanResult.route) {
        val imageUri = navController.previousBackStackEntry?.savedStateHandle?.get<Uri>("image_uri")
        val result = navController.previousBackStackEntry?.savedStateHandle?.get<DetectResult>("result")

        if (imageUri != null && result != null) {
            ScanResultScreen(
                navController = navController,
                imageUri = imageUri,
                result = result
            )
        }
    }
}
