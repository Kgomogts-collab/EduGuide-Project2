package com.example.project2.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun TaskProgress(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {

        item { ProgressItem(taskName = "Design Login Screen",progress = 0.45f) }
        item { ProgressItem(taskName = "Database creation",progress = 0.76f) }
        item { ProgressItem(taskName = "Project Research",progress = 0.23f) }
        item { ProgressItem(taskName = "Design an entity",progress = 0.45f) }
        item { ProgressItem(taskName = "Add button",progress = 0.78f) }
        item { ProgressItem(taskName = "Design the welcome screen",progress = 1f) }
        item { ProgressItem(taskName = "Implement Encryption",progress = 0.83f) }

    }

}
//

@Composable
fun ProgressItem(modifier: Modifier = Modifier,taskName: String,progress: Float) {
    Row(modifier = modifier.fillMaxWidth()){
        Text(taskName)
        LinearProgressIndicator(progress = { progress },modifier = Modifier.fillMaxWidth(0.75f))
        Text((progress*100).toString() + "%")
    }
}