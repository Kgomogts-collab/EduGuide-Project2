package com.example.project2_ui.screen_notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.project2_ui.data_model.FileType
import com.example.project2_ui.data_model.Note
import com.example.project2_ui.components.DotIndicator
import com.example.project2_ui.components.SectionHeader
import com.example.project2_ui.components.Tag
import com.example.project2_ui.theme.*

private enum class NoteMode(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    RECORD("Record", Icons.Filled.Mic),
    TRANSLATE("Translate", Icons.Filled.Translate),
    SPEECH("Text-to-Speech", Icons.Filled.RecordVoiceOver)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen() {
    var mode by remember { mutableStateOf(NoteMode.RECORD) }
    var isLive by remember { mutableStateOf(true) }
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        containerColor = Parchment,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { isLive = !isLive },
                containerColor = Coral,
                contentColor = Color.White,
                icon = { Icon(if (isLive) Icons.Filled.Stop else Icons.Filled.FiberManualRecord, null) },
                text = { Text(if (isLive) "End session" else "Start listening") }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            item {
                Column {
                    Text("Live Class & Notes", style = MaterialTheme.typography.displaySmall, color = InkNavy)
                    Spacer(Modifier.height(4.dp))
                    Text(
                        "COS 212 · Database Design",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedText
                    )
                }
            }

            item { LiveStatusBanner(isLive = isLive) }

            item { ModeSelector(selected = mode, onSelect = { mode = it }) }

            item { LiveTranscriptCard(mode = mode) }

            item { AiSummaryCard() }

            item {
                SectionHeader(
                    title = "Your notes",
                    subtitle = "${MockData.notes.size} saved this week"
                )
            }

            item {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search notes, courses, keywords…") },
                    leadingIcon = { Icon(Icons.Filled.Search, null, tint = MutedText) },
                    shape = EduGuideShapes.medium,
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = InkNavy,
                        unfocusedBorderColor = DividerTone,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )
            }

            val filtered = MockData.notes.filter {
                searchQuery.isBlank() ||
                        it.title.contains(searchQuery, ignoreCase = true) ||
                        it.courseTag.contains(searchQuery, ignoreCase = true)
            }
            items(filtered) { note -> NoteCard(note) }

            if (filtered.isEmpty()) {
                item {
                    Text(
                        "No notes match \"$searchQuery\".",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedText,
                        modifier = Modifier.padding(vertical = 24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun LiveStatusBanner(isLive: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(CardShapeSignature)
            .background(if (isLive) InkNavy else ParchmentDim)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        DotIndicator(color = if (isLive) Coral else MutedText, size = 10.dp)
        Column(Modifier.weight(1f)) {
            Text(
                if (isLive) "Listening in real time" else "No active session",
                style = MaterialTheme.typography.titleMedium,
                color = if (isLive) Color.White else CharcoalText
            )
            Text(
                if (isLive) "Auto-transcribing · timestamps on key moments" else "Tap start to begin transcribing your next class",
                style = MaterialTheme.typography.bodyMedium,
                color = if (isLive) Color.White.copy(alpha = 0.75f) else MutedText
            )
        }
        if (isLive) {
            Text("12:34", style = MaterialTheme.typography.titleMedium, color = Color.White)
        }
    }
}

@Composable
private fun ModeSelector(selected: NoteMode, onSelect: (NoteMode) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(ChipShape)
            .background(ParchmentDim)
            .padding(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        NoteMode.values().forEach { m ->
            val isSelected = m == selected
            Row(
                modifier = Modifier
                    .weight(1f)
                    .clip(ChipShape)
                    .background(if (isSelected) InkNavy else Color.Transparent)
                    .clickable { onSelect(m) }
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    m.icon, null,
                    tint = if (isSelected) Color.White else MutedText,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(Modifier.width(6.dp))
                Text(
                    m.label,
                    style = MaterialTheme.typography.labelLarge,
                    color = if (isSelected) Color.White else MutedText
                )
            }
        }
    }
}

@Composable
private fun LiveTranscriptCard(mode: NoteMode) {
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            when (mode) {
                NoteMode.RECORD -> {
                    Text("Transcript", style = MaterialTheme.typography.titleMedium, color = InkNavy)
                    Spacer(Modifier.height(10.dp))
                    MockData.transcript.forEach { line ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Text(
                                line.timestamp,
                                style = MaterialTheme.typography.labelSmall,
                                color = if (line.isKeyMoment) Coral else MutedText,
                                modifier = Modifier.width(44.dp)
                            )
                            Column(Modifier.weight(1f)) {
                                Text(
                                    line.speaker,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = MutedText
                                )
                                Text(
                                    line.text,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = CharcoalText,
                                    fontWeight = if (line.isKeyMoment) FontWeight.SemiBold else FontWeight.Normal
                                )
                            }
                            if (line.isKeyMoment) {
                                Tag("KEY", AmberFaint, Amber)
                            }
                        }
                    }
                }
                NoteMode.TRANSLATE -> {
                    Text("Real-time translation", style = MaterialTheme.typography.titleMedium, color = InkNavy)
                    Spacer(Modifier.height(12.dp))
                    Text("Translate to", style = MaterialTheme.typography.bodyMedium, color = MutedText)
                    Spacer(Modifier.height(6.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        listOf("isiZulu", "Afrikaans", "Sesotho", "English").forEachIndexed { i, lang ->
                            Tag(lang, if (i == 0) InkNavy else ParchmentDim, if (i == 0) Color.White else CharcoalText)
                        }
                    }
                    Spacer(Modifier.height(14.dp))
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .clip(EduGuideShapes.medium)
                            .background(ParchmentDim)
                            .padding(14.dp)
                    ) {
                        Text(
                            "\"Lo mfundi kufanele athumele umsebenzi ngaphambi komhla ophelele.\"",
                            style = MaterialTheme.typography.bodyMedium,
                            color = CharcoalText
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                    FilledTonalButton(
                        onClick = {},
                        colors = ButtonDefaults.filledTonalButtonColors(containerColor = InkNavy, contentColor = Color.White)
                    ) {
                        Icon(Icons.Filled.PlayArrow, null, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(6.dp))
                        Text("Play audio")
                    }
                }
                NoteMode.SPEECH -> {
                    Text("Text to speech", style = MaterialTheme.typography.titleMedium, color = InkNavy)
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Hover or select any note text to have it read aloud — helpful for accessibility and multitasking while reviewing.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedText
                    )
                    Spacer(Modifier.height(14.dp))
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clip(EduGuideShapes.medium)
                            .background(SageFaint)
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Icon(Icons.Filled.VolumeUp, null, tint = Sage)
                        Text("Reading: \"Functional dependency exercise…\"", style = MaterialTheme.typography.bodyMedium, color = CharcoalText)
                    }
                }
            }
        }
    }
}

@Composable
private fun AiSummaryCard() {
    var expanded by remember { mutableStateOf(true) }
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = InkNavy),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(18.dp)) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { expanded = !expanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.AutoAwesome, null, tint = AmberFaint)
                    Spacer(Modifier.width(8.dp))
                    Text("AI Class Summary", style = MaterialTheme.typography.titleMedium, color = Color.White)
                }
                Icon(
                    if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    null, tint = Color.White
                )
            }
            AnimatedVisibility(visible = expanded) {
                Column {
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "Today's class covered normalisation up to 3NF, with a worked example on functional " +
                                "dependencies. Two exam-relevant moments were flagged automatically. No jargon — " +
                                "generated straight from your live transcript.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.85f)
                    )
                    Spacer(Modifier.height(14.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(containerColor = Coral, contentColor = Color.White),
                            shape = EduGuideShapes.medium
                        ) {
                            Icon(Icons.Filled.Download, null, modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(6.dp))
                            Text("Download summary")
                        }
                        OutlinedButton(
                            onClick = {},
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
                            shape = EduGuideShapes.medium
                        ) {
                            Text("View full note")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun NoteCard(note: Note) {
    Card(
        shape = CardShapeSignature,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(ParchmentDim),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    when (note.fileType) {
                        FileType.PDF -> Icons.Filled.PictureAsPdf
                        FileType.DOCX -> Icons.Filled.Description
                        FileType.TXT -> Icons.Filled.Notes
                    },
                    null, tint = InkNavy, modifier = Modifier.size(20.dp)
                )
            }
            Column(Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(note.title, style = MaterialTheme.typography.titleMedium, color = CharcoalText)
                }
                Spacer(Modifier.height(4.dp))
                Text(
                    note.preview,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedText,
                    maxLines = 2
                )
                Spacer(Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                    Tag(note.courseTag, InkNavyFaint, InkNavy)
                    Text(note.dateCreated, style = MaterialTheme.typography.labelSmall, color = MutedText)
                    if (note.hasAiSummary) {
                        Spacer(Modifier.width(2.dp))
                        Icon(Icons.Filled.AutoAwesome, null, tint = Amber, modifier = Modifier.size(13.dp))
                    }
                }
            }
        }
    }
}