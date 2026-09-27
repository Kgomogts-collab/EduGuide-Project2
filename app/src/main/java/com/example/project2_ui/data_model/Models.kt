package com.example.project2_ui.data_model

data class Note(
    val id: String,
    val title: String,
    val course: String,
    val topic: String,
    val content: String,
    val tags: List<String> = emptyList(),
    val dateUpdated: String,
    val isFavorite: Boolean = false,
    val isReviewed: Boolean = false
)

enum class ModuleStatus { ON_TRACK, IN_PROGRESS, NEEDS_ATTENTION }

data class ModuleProgress(
    val name: String,
    val percent: Int,
    val status: ModuleStatus
)

data class WeeklyStats(
    val studySessions: Int,
    val notesCreated: Int,
    val tasksCompleted: Int,
    val hoursStudied: Int,
    val hoursGoal: Int
)

data class Deadline(
    val title: String,
    val dueLabel: String,
    val progressPercent: Int
)

data class StudyGoal(
    val title: String,
    val currentPercent: Int
)

data class AttentionItem(
    val module: String,
    val message: String
)