package com.example.project2_ui.screen_notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.project2_ui.data_model.MockData
import com.example.project2_ui.data_model.ContributionTask
import com.example.project2_ui.data_model.GroupMember
import com.example.project2_ui.data_model.Importance
import com.example.project2_ui.components.*
import com.example.project2_ui.theme.*

private const val LOW_PARTICIPATION_THRESHOLD = 15

@Composable
fun ProgressScreen() {
    val flaggedMembers = MockData.groupMembers.filter { it.participationPercent < LOW_PARTICIPATION_THRESHOLD }

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
                Column {
                    Text("Project Hub", style = MaterialTheme.typography.displaySmall, color = InkNavy)
                    Spacer(Modifier.height(4.dp))
                    Text("EDU-GUIDE Capstone · Group 4", style = MaterialTheme.typography.bodyMedium, color = MutedText)
                }
            }

            item { ProjectHubSummaryCard() }

            if (flaggedMembers.isNotEmpty()) {
                item { ConflictAlertBanner(flaggedMembers) }
            }

            item {
                SectionHeader(
                    title = "Your weighted contribution",
                    subtitle = "${MockData.contributionTasks.sumOf { it.weightedPercent }}% of group total"
                )
            }
            items(MockData.contributionTasks) { task -> ContributionTaskCard(task) }

            item {
                SectionHeader(title = "Team participation", subtitle = "Verified by peers, not self-reported")
            }
            item { ParticipationOverviewCard() }

            item { ReflectionLogCard() }
        }
    }
}

@Composable
private fun ProjectHubSummaryCard() {
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = InkNavy),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column(Modifier.weight(1f)) {
                    Text("Smart Contribution Tracker", style = MaterialTheme.typography.titleLarge, color = Color.White)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "Fairness-first group evaluation, not a task list.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.7f)
                    )
                }
                ProgressRing(
                    percent = 63,
                    ringColor = Coral,
                    trackColor = Color.White.copy(alpha = 0.18f),
                    centerLabel = "63%"
                )
            }
            Spacer(Modifier.height(18.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                StatPill(label = "Hours logged", value = "15.0h", modifier = Modifier.weight(1f))
                StatPill(label = "Peer rating", value = "4.6 / 5", modifier = Modifier.weight(1f))
                StatPill(label = "Tasks done", value = "4 / 5", modifier = Modifier.weight(1f))
            }
            Spacer(Modifier.height(14.dp))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Coral, contentColor = Color.White),
                shape = EduGuideShapes.medium
            ) {
                Icon(Icons.Filled.UploadFile, null, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text("Submit contribution report")
            }
        }
    }
}

@Composable
private fun StatPill(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .clip(EduGuideShapes.medium)
            .background(Color.White.copy(alpha = 0.08f))
            .padding(vertical = 10.dp, horizontal = 10.dp)
    ) {
        Text(value, style = MaterialTheme.typography.titleMedium, color = Color.White, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.65f))
    }
}

@Composable
private fun ConflictAlertBanner(flagged: List<GroupMember>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CardShapeSignature)
            .background(CoralFaint)
            .padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(Icons.Filled.WarningAmber, null, tint = CoralDark)
        Column {
            Text("Low participation warning", style = MaterialTheme.typography.titleMedium, color = CoralDark)
            Spacer(Modifier.height(2.dp))
            Text(
                "${flagged.joinToString { it.name }} is below the 15% contribution threshold. " +
                        "Flagged automatically for lecturer review — no action needed from you.",
                style = MaterialTheme.typography.bodyMedium,
                color = CharcoalText
            )
        }
    }
}

@Composable
private fun ContributionTaskCard(task: ContributionTask) {
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(task.title, style = MaterialTheme.typography.titleMedium, color = CharcoalText, modifier = Modifier.weight(1f))
                Text("${task.weightedPercent}%", style = MaterialTheme.typography.titleMedium, color = InkNavy, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(8.dp))
            WeightedBar(percent = task.weightedPercent, color = difficultyColor(task.difficulty))
            Spacer(Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Tag(task.difficulty.name.lowercase().replaceFirstChar { it.uppercase() }, difficultyColor(task.difficulty).copy(alpha = 0.15f), difficultyColor(task.difficulty))
                Tag("${task.hours}h", ParchmentDim, MutedText)
                Tag(
                    if (task.importance == Importance.HIGH) "High impact" else task.importance.name.lowercase().replaceFirstChar { it.uppercase() },
                    ParchmentDim, MutedText
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    if (task.peerVerified) Icons.Filled.Verified else Icons.Filled.HourglassBottom,
                    null,
                    tint = if (task.peerVerified) Sage else Amber,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}

@Composable
private fun ParticipationOverviewCard() {
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            MockData.groupMembers.forEachIndexed { index, member ->
                MemberRow(member)
                if (index != MockData.groupMembers.lastIndex) {
                    Divider(color = DividerTone, modifier = Modifier.padding(vertical = 10.dp))
                }
            }
        }
    }
}

@Composable
private fun MemberRow(member: GroupMember) {
    val flagged = member.participationPercent < LOW_PARTICIPATION_THRESHOLD
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(14.dp)) {
        Box(
            Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(if (member.isCurrentUser) InkNavy else ParchmentDim),
            contentAlignment = Alignment.Center
        ) {
            Text(
                member.name.first().toString(),
                color = if (member.isCurrentUser) Color.White else CharcoalText,
                style = MaterialTheme.typography.titleMedium
            )
        }
        Column(Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(member.name, style = MaterialTheme.typography.titleMedium, color = CharcoalText)
                if (flagged) {
                    Spacer(Modifier.width(6.dp))
                    Icon(Icons.Filled.WarningAmber, null, tint = Coral, modifier = Modifier.size(14.dp))
                }
            }
            Spacer(Modifier.height(6.dp))
            WeightedBar(
                percent = member.participationPercent,
                color = if (flagged) Coral else Sage
            )
        }
        Column(horizontalAlignment = Alignment.End) {
            Text("${member.participationPercent}%", style = MaterialTheme.typography.titleMedium, color = InkNavy, fontWeight = FontWeight.Bold)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Star, null, tint = Amber, modifier = Modifier.size(12.dp))
                Spacer(Modifier.width(2.dp))
                Text("${member.peerRating}", style = MaterialTheme.typography.labelSmall, color = MutedText)
            }
        }
    }
}

@Composable
private fun ReflectionLogCard() {
    var text by remember { mutableStateOf("") }
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = SageFaint),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                Column {
                    Text("Reflection log", style = MaterialTheme.typography.titleLarge, color = InkNavy)
                    Text("Due ${MockData.reflection.dueDate} · permanent once submitted", style = MaterialTheme.typography.bodyMedium, color = MutedText)
                }
                Icon(Icons.Filled.EditNote, null, tint = Sage)
            }
            Spacer(Modifier.height(12.dp))
            OutlinedTextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 90.dp),
                placeholder = { Text("What did you contribute? What challenges did you face?") },
                shape = EduGuideShapes.medium,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Sage,
                    unfocusedBorderColor = Color.White,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
            Spacer(Modifier.height(12.dp))
            Button(
                onClick = {},
                enabled = text.isNotBlank(),
                colors = ButtonDefaults.buttonColors(containerColor = Sage, contentColor = Color.White),
                shape = EduGuideShapes.medium
            ) {
                Icon(Icons.Filled.Send, null, modifier = Modifier.size(16.dp))
                Spacer(Modifier.width(8.dp))
                Text("Submit reflection")
            }
        }
    }
}