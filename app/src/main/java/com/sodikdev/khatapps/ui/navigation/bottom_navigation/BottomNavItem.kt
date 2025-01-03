package com.sodikdev.khatapps.ui.navigation.bottom_navigation

import androidx.compose.ui.graphics.painter.Painter
import com.sodikdev.khatapps.ui.navigation.Screen

data class BottomNavItem(
    val title: String,
    val icon: Painter,
    val screen: Screen
)