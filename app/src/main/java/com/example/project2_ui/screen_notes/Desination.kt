package com.example.project2_ui.screen_notes

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.EditNote
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destination(val route: String, val label: String, val icon: ImageVector) {
    data object Notes : Destination("notes", "Notes", Icons.Filled.Notes)
    data object Summary : Destination("summary", "AI Summary", Icons.Filled.AutoAwesome)
    data object Progress : Destination("progress", "Progress", Icons.Filled.TrendingUp)
    data object Reflection : Destination("reflection", "Reflection", Icons.Filled.EditNote)

    companion object {
        val bottomNavItems = listOf(Notes, Summary, Progress, Reflection)
    }
}