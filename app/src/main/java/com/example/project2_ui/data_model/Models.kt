package com.example.project2_ui.data_model

enum class Difficulty { EASY, MEDIUM, HARD }
enum class Importance { LOW, MEDIUM, HIGH }
enum class FileType { PDF, DOCX, TXT }

data class Note(
    val id: String,
    val title: String,
    val courseTag: String,
    val preview: String,
    val dateCreated: String,
    val fileType: FileType,
    val hasAiSummary: Boolean
)

data class TranscriptLine(
    val timestamp: String,
    val speaker: String,
    val text: String,
    val isKeyMoment: Boolean = false
)

data class ContributionTask(
    val id: String,
    val title: String,
    val difficulty: Difficulty,
    val importance: Importance,
    val hours: Double,
    val peerVerified: Boolean,
    val weightedPercent: Int
)

data class GroupMember(
    val id: String,
    val name: String,
    val participationPercent: Int,
    val peerRating: Double, // out of 5
    val isCurrentUser: Boolean = false
)

data class ReflectionLogEntry(
    val dueDate: String,
    val submitted: Boolean,
    val text: String = ""
)