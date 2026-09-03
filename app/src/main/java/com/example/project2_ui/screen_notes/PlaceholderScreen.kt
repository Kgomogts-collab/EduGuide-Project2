package com.example.project2_ui.screen_notes

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.project2_ui.theme.InkNavy
import com.example.project2_ui.theme.MutedText
import com.example.project2_ui.theme.Parchment

@Composable
fun PlaceholderScreen(title: String) {
    Scaffold(containerColor = Parchment) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(Icons.Filled.Construction, null, tint = MutedText, modifier = Modifier.size(40.dp))
            Spacer(Modifier.height(12.dp))
            Text(title, style = MaterialTheme.typography.titleLarge, color = InkNavy)
            Spacer(Modifier.height(6.dp))
            Text(
                "This tab lives inside Notes / Progress in the current build.",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedText,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}