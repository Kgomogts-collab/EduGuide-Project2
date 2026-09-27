package com.example.project2_ui.screen_notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.example.project2_ui.data_model.Note
import com.example.project2_ui.theme.*

private enum class NotesView { LIST, DETAIL, CREATE }

@Composable
fun NotesScreen() {
    var view by remember { mutableStateOf(NotesView.LIST) }
    var notes by remember { mutableStateOf(MockData.notes) }
    var selectedNote by remember { mutableStateOf<Note?>(null) }

    when (view) {
        NotesView.LIST -> NotesListView(
            notes = notes,
            onOpenNote = { note ->
                selectedNote = note
                view = NotesView.DETAIL
            },
            onCreateClick = { view = NotesView.CREATE }
        )
        NotesView.DETAIL -> {
            val note = selectedNote
            if (note != null) {
                NoteDetailView(
                    note = note,
                    onBack = { view = NotesView.LIST },
                    onToggleFavorite = {
                        notes = notes.map { if (it.id == note.id) it.copy(isFavorite = !it.isFavorite) else it }
                        selectedNote = notes.first { it.id == note.id }
                    },
                    onToggleReviewed = {
                        notes = notes.map { if (it.id == note.id) it.copy(isReviewed = !it.isReviewed) else it }
                        selectedNote = notes.first { it.id == note.id }
                    }
                )
            }
        }
        NotesView.CREATE -> CreateNoteView(
            onCancel = { view = NotesView.LIST },
            onSave = { newNote ->
                notes = notes + newNote
                view = NotesView.LIST
            }
        )
    }
}

@Composable
private fun NotesListView(
    notes: List<Note>,
    onOpenNote: (Note) -> Unit,
    onCreateClick: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val filtered = notes.filter { note ->
        val matchesSearch = searchQuery.isBlank() ||
                note.title.contains(searchQuery, ignoreCase = true) ||
                note.topic.contains(searchQuery, ignoreCase = true)
        val matchesFilter = selectedFilter == "All" || note.course.contains(selectedFilter, ignoreCase = true)
        matchesSearch && matchesFilter
    }

    Scaffold(containerColor = Parchment) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp),
            contentPadding = PaddingValues(top = 16.dp, bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Text("Notes", style = MaterialTheme.typography.displaySmall, color = InkNavy)
                Text(
                    "Everything you've written, in one place",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MutedText
                )
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        modifier = Modifier.weight(1f),
                        placeholder = { Text("Search notes...") },
                        leadingIcon = { Icon(Icons.Filled.Search, null, tint = MutedText) },
                        singleLine = true,
                        shape = EduGuideShapes.medium
                    )
                    Button(
                        onClick = onCreateClick,
                        colors = ButtonDefaults.buttonColors(containerColor = Coral, contentColor = Color.White),
                        shape = EduGuideShapes.medium
                    ) {
                        Icon(Icons.Filled.Add, null, modifier = Modifier.size(18.dp))
                    }
                }
            }

            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    items(MockData.courseFilters) { filterName ->
                        FilterChip(
                            selected = selectedFilter == filterName,
                            onClick = { selectedFilter = filterName },
                            label = { Text(filterName) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = InkNavy,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            item {
                Text("My Notes", style = MaterialTheme.typography.titleMedium, color = InkNavy)
            }

            if (filtered.isEmpty()) {
                item {
                    Text(
                        "No notes found.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MutedText,
                        modifier = Modifier.padding(vertical = 20.dp)
                    )
                }
            }

            items(filtered) { note ->
                NoteListCard(note = note, onClick = { onOpenNote(note) })
            }
        }
    }
}

@Composable
private fun NoteListCard(note: Note, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        shape = EduGuideShapes.medium,
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Description, null, tint = InkNavy, modifier = Modifier.size(18.dp))
                Spacer(Modifier.width(8.dp))
                Text(note.title, style = MaterialTheme.typography.titleMedium, color = CharcoalText, modifier = Modifier.weight(1f))
                if (note.isFavorite) {
                    Icon(Icons.Filled.Star, null, tint = Amber, modifier = Modifier.size(18.dp))
                }
                if (note.isReviewed) {
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Filled.CheckCircle, null, tint = Sage, modifier = Modifier.size(18.dp))
                }
            }
            Spacer(Modifier.height(4.dp))
            Text(
                "${note.course} · ${note.dateUpdated}",
                style = MaterialTheme.typography.bodyMedium,
                color = MutedText
            )
        }
    }
}

@Composable
private fun NoteDetailView(
    note: Note,
    onBack: () -> Unit,
    onToggleFavorite: () -> Unit,
    onToggleReviewed: () -> Unit
) {
    var showSummary by remember { mutableStateOf(false) }

    Scaffold(containerColor = Parchment) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Spacer(Modifier.height(16.dp))
            TextButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, null, tint = InkNavy)
                Spacer(Modifier.width(4.dp))
                Text("Back", color = InkNavy)
            }

            Text(note.title, style = MaterialTheme.typography.displaySmall, color = InkNavy)
            Text("Course: ${note.course}", style = MaterialTheme.typography.bodyMedium, color = MutedText)
            Text("Topic: ${note.topic}", style = MaterialTheme.typography.bodyMedium, color = MutedText)

            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                note.tags.forEach { tag ->
                    Box(
                        Modifier
                            .clip(ChipShape)
                            .background(InkNavyFaint)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    ) {
                        Text("#$tag", style = MaterialTheme.typography.labelSmall, color = InkNavy)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))
            Card(
                shape = EduGuideShapes.medium,
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Text(
                    note.content,
                    style = MaterialTheme.typography.bodyLarge,
                    color = CharcoalText,
                    modifier = Modifier.padding(16.dp)
                )
            }

            if (showSummary) {
                Spacer(Modifier.height(12.dp))
                Card(
                    shape = EduGuideShapes.medium,
                    colors = CardDefaults.cardColors(containerColor = SageFaint)
                ) {
                    Column(Modifier.padding(16.dp)) {
                        Text("AI Summary", style = MaterialTheme.typography.titleMedium, color = Sage)
                        Spacer(Modifier.height(8.dp))
                        Text(
                            "Key Points\n\n• ${note.topic} covers the core ideas of this section.\n• Focus on definitions and worked examples for revision.\n• Try answering: what is the purpose of ${note.topic}?",
                            style = MaterialTheme.typography.bodyMedium,
                            color = CharcoalText
                        )
                    }
                }
            }

            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { /* edit not wired up yet */ }, shape = EduGuideShapes.medium) {
                    Text("Edit")
                }
                Button(
                    onClick = { showSummary = !showSummary },
                    colors = ButtonDefaults.buttonColors(containerColor = InkNavy, contentColor = Color.White),
                    shape = EduGuideShapes.medium
                ) {
                    Text(if (showSummary) "Hide Summary" else "Generate Summary")
                }
            }
            Spacer(Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = onToggleReviewed, shape = EduGuideShapes.medium) {
                    Icon(Icons.Filled.CheckCircle, null, tint = Sage, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(if (note.isReviewed) "Reviewed" else "Mark as Reviewed")
                }
                OutlinedButton(onClick = onToggleFavorite, shape = EduGuideShapes.medium) {
                    Icon(Icons.Filled.Star, null, tint = Amber, modifier = Modifier.size(16.dp))
                    Spacer(Modifier.width(6.dp))
                    Text(if (note.isFavorite) "Pinned" else "Pin")
                }
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Composable
private fun CreateNoteView(onCancel: () -> Unit, onSave: (Note) -> Unit) {
    var title by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var topic by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Scaffold(containerColor = Parchment) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(horizontal = 20.dp)
        ) {
            Spacer(Modifier.height(16.dp))
            Text("New Note", style = MaterialTheme.typography.displaySmall, color = InkNavy)
            Spacer(Modifier.height(16.dp))

            OutlinedTextField(
                value = title, onValueChange = { title = it },
                label = { Text("Title") }, modifier = Modifier.fillMaxWidth(), shape = EduGuideShapes.medium
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = course, onValueChange = { course = it },
                label = { Text("Subject / Course") }, modifier = Modifier.fillMaxWidth(), shape = EduGuideShapes.medium
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = topic, onValueChange = { topic = it },
                label = { Text("Topic") }, modifier = Modifier.fillMaxWidth(), shape = EduGuideShapes.medium
            )
            Spacer(Modifier.height(10.dp))
            OutlinedTextField(
                value = content, onValueChange = { content = it },
                label = { Text("Note content") },
                modifier = Modifier.fillMaxWidth().heightIn(min = 120.dp),
                shape = EduGuideShapes.medium
            )

            Spacer(Modifier.height(20.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedButton(onClick = onCancel, shape = EduGuideShapes.medium) {
                    Text("Cancel")
                }
                Button(
                    onClick = {
                        if (title.isNotBlank()) {
                            onSave(
                                Note(
                                    id = "n${System.currentTimeMillis()}",
                                    title = title,
                                    course = course.ifBlank { "General" },
                                    topic = topic,
                                    content = content,
                                    dateUpdated = "Updated just now"
                                )
                            )
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Coral, contentColor = Color.White),
                    shape = EduGuideShapes.medium
                ) {
                    Text("Save Note")
                }
            }
        }
    }
}