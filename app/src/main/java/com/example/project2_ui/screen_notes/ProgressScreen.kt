package com.example.project2_ui.screen_notes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project2_ui.data_model.AttentionItem
import com.example.project2_ui.data_model.Deadline
import com.example.project2_ui.data_model.ModuleProgress
import com.example.project2_ui.data_model.ModuleStatus
import com.example.project2_ui.data_model.MockData
import com.example.project2_ui.data_model.StudyGoal
import com.example.project2_ui.theme.*

@Composable
fun ProgressScreen() {
    Scaffold(containerColor = Parchment) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Text("Progress", style = MaterialTheme.typography.displaySmall, color = InkNavy)
                Text(
                    "See how you're doing and what needs attention",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedText
                )
            }

            item { OverallProgressCard() }

            item { Text("My Modules", style = MaterialTheme.typography.titleMedium, color = InkNavy) }
            item {
                Card(
                    shape = EduGuideShapes.medium,
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        MockData.moduleProgress.forEachIndexed { index, module ->
                            ModuleRow(module)
                            if (index != MockData.moduleProgress.lastIndex) {
                                Spacer(Modifier.height(14.dp))
                            }
                        }
                    }
                }
            }

            item { Text("This Week", style = MaterialTheme.typography.titleMedium, color = InkNavy) }
            item { WeeklyStatsCard() }

            item { Text("Upcoming", style = MaterialTheme.typography.titleMedium, color = InkNavy) }
            items(MockData.deadlines) { deadline -> DeadlineCard(deadline) }

            item { Text("Study Goals", style = MaterialTheme.typography.titleMedium, color = InkNavy) }
            items(MockData.studyGoals) { goal -> StudyGoalCard(goal) }

            if (MockData.attentionItems.isNotEmpty()) {
                item { Text("Needs Attention", style = MaterialTheme.typography.titleMedium, color = InkNavy) }
                item { AttentionCard() }
            }
        }
    }
}

@Composable
private fun OverallProgressCard() {
    Card(
        shape = EduGuideShapes.large,
        colors = CardDefaults.cardColors(containerColor = InkNavy)
    ) {
        Column(Modifier.padding(20.dp)) {
            Text("Overall Study Progress", style = MaterialTheme.typography.titleMedium, color = Color.White)
            Spacer(Modifier.height(10.dp))
            LinearProgressIndicator(
                progress = { MockData.overallProgressPercent / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(10.dp),
                color = Coral,
                trackColor = Color.White.copy(alpha = 0.2f)
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "${MockData.overallProgressPercent}% complete",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
private fun ModuleRow(module: ModuleProgress) {
    val (statusLabel, statusColor) = when (module.status) {
        ModuleStatus.ON_TRACK -> "On Track" to Sage
        ModuleStatus.IN_PROGRESS -> "In Progress" to Amber
        ModuleStatus.NEEDS_ATTENTION -> "Needs Attention" to Coral
    }
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(module.name, style = MaterialTheme.typography.titleMedium, color = CharcoalText)
            Text("${module.percent}%", style = MaterialTheme.typography.titleMedium, color = InkNavy, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(6.dp))
        LinearProgressIndicator(
            progress = { module.percent / 100f },
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = statusColor,
            trackColor = DividerTone
        )
        Spacer(Modifier.height(4.dp))
        Text(statusLabel, style = MaterialTheme.typography.labelSmall, color = statusColor)
    }
}

@Composable
private fun WeeklyStatsCard() {
    val stats = MockData.weeklyStats
    Card(
        shape = EduGuideShapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(Modifier.padding(16.dp)) {
            StatLine(Icons.Filled.MenuBook, "${stats.studySessions} Study Sessions")
            StatLine(Icons.Filled.EditNote, "${stats.notesCreated} Notes Created")
            StatLine(Icons.Filled.CheckCircle, "${stats.tasksCompleted} Tasks Completed")
            StatLine(Icons.Filled.Timer, "${stats.hoursStudied}/${stats.hoursGoal} Hours Studied")
        }
    }
}

@Composable
private fun StatLine(icon: androidx.compose.ui.graphics.vector.ImageVector, text: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Icon(icon, null, tint = InkNavy, modifier = Modifier.size(18.dp))
        Spacer(Modifier.width(10.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium, color = CharcoalText)
    }
}

@Composable
private fun DeadlineCard(deadline: Deadline) {
    Card(
        shape = EduGuideShapes.medium,
        colors = CardDefaults.cardColors(containerColor = AmberFaint)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(deadline.title, style = MaterialTheme.typography.titleMedium, color = CharcoalText)
            Text(deadline.dueLabel, style = MaterialTheme.typography.bodyMedium, color = MutedText)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { deadline.progressPercent / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Amber,
                trackColor = Color.White
            )
            Spacer(Modifier.height(4.dp))
            Text("Progress: ${deadline.progressPercent}%", style = MaterialTheme.typography.labelSmall, color = MutedText)
        }
    }
}

@Composable
private fun StudyGoalCard(goal: StudyGoal) {
    Card(
        shape = EduGuideShapes.medium,
        colors = CardDefaults.cardColors(containerColor = SageFaint)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(goal.title, style = MaterialTheme.typography.titleMedium, color = CharcoalText)
            Spacer(Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = { goal.currentPercent / 100f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = Sage,
                trackColor = Color.White
            )
            Spacer(Modifier.height(4.dp))
            Text("${goal.currentPercent}%", style = MaterialTheme.typography.labelSmall, color = MutedText)
        }
    }
}

@Composable
private fun AttentionCard() {
    Card(
        shape = EduGuideShapes.medium,
        colors = CardDefaults.cardColors(containerColor = CoralFaint)
    ) {
        Column(Modifier.padding(16.dp)) {
            MockData.attentionItems.forEach { item: AttentionItem ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    Icon(Icons.Filled.WarningAmber, null, tint = CoralDark, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(8.dp))
                    Text(
                        "${item.module}: ${item.message}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = CharcoalText
                    )
                }
            }
        }
    }
}