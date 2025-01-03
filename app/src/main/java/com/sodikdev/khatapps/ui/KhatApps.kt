package com.sodikdev.khatapps.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
import com.sodikdev.khatapps.R
import com.sodikdev.khatapps.ui.navigation.Screen
import com.sodikdev.khatapps.ui.navigation.bottom_navigation.BottomNavItem

@Composable
fun KhatApps(
    navController: NavHostController = rememberNavController(),
) {

    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    val shouldShowBottomBar = when (currentDestination?.route) {
        Screen.Home.route, Screen.History.route,
        Screen.About.route, -> true
        else -> false
    }

    Scaffold(
        bottomBar = {
            if (!shouldShowBottomBar) {
                return@Scaffold
            }
            BottomBar(navController = navController, currentDestination = currentDestination)
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomBar(
    navController: NavController,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        /*BottomNavItem(
            title = "Home",
            icon = Icons.Rounded.Home,
            screen = Screen.Home,
        ),*/
        BottomNavItem(
            title = "Home",
            icon = painterResource(id = R.drawable.home_svgrepo_com), // Ganti dengan gambar drawable
            screen = Screen.Home,
        ),
        /*BottomNavItem(
            title = "Catatan",
            icon = Icons.Rounded.History,
            screen = Screen.History,
        ),*/
        BottomNavItem(
            title = "Stock",
            icon = painterResource(id = R.drawable.info_circle_svgrepo_com), // Ganti dengan gambar drawable
            screen = Screen.About,
        )
        /*BottomNavItem(
            title = "Stock",
            icon = Icons.Rounded.Info,
            screen = Screen.About,
        )*/

    )
    NavigationBar(
        modifier = modifier,
    ) {
        items.map { item ->
            NavigationBarItem(
                selected = currentDestination?.route == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph[Screen.Home.route].id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        painter = item.icon,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.White,
                    selectedIconColor = Color.Black
                )
            )
        }
    }
}