package com.example.project2_ui.screen_notes

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.project2_ui.theme.Coral
import com.example.project2_ui.theme.InkNavy
import com.example.project2_ui.theme.MutedText
import com.example.project2_ui.theme.ParchmentCard

@Composable
fun EduGuideNavGraph() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { EduGuideBottomBar(navController) }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = Destination.Notes.route,
            modifier = Modifier.padding(bottom = padding.calculateBottomPadding())
        ) {
            composable(Destination.Notes.route) { NotesScreen() }
            composable(Destination.Summary.route) { PlaceholderScreen("AI Summary") }
            composable(Destination.Progress.route) { ProgressScreen() }
            composable(Destination.Reflection.route) { PlaceholderScreen("Reflection Log") }
        }
    }
}

@Composable
private fun EduGuideBottomBar(navController: androidx.navigation.NavHostController) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    NavigationBar(
        containerColor = ParchmentCard,
        tonalElevation = 0.dp,
        modifier = Modifier.height(72.dp)
    ) {
        Destination.bottomNavItems.forEach { dest ->
            val selected = currentRoute == dest.route
            NavigationBarItem(
                selected = selected,
                onClick = {
                    navController.navigate(dest.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(dest.icon, contentDescription = dest.label) },
                label = { Text(dest.label, style = MaterialTheme.typography.labelSmall) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = InkNavy,
                    selectedTextColor = InkNavy,
                    unselectedIconColor = MutedText,
                    unselectedTextColor = MutedText,
                    indicatorColor = Coral.copy(alpha = 0.15f)
                )
            )
        }
    }
}