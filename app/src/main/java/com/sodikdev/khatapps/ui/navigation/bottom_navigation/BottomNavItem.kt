package com.sodikdev.khatapps.ui.navigation.bottom_navigation

import androidx.compose.ui.graphics.vector.ImageVector
import com.sodikdev.khatapps.ui.navigation.Screen

data class BottomNavItem(
    val title : String,
    val icon : ImageVector,
    val screen: Screen
)