package com.example.project2database.database

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class User(
    @PrimaryKey(autoGenerate = true) val userId: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: String,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true) val subjectId: Int = 0,
    val subjectName: String,
    val subjectCode: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class Notebook(
    @PrimaryKey(autoGenerate = true) val notebookId: Int = 0,
    val notebookName: String,
    val userId: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val noteId: Int = 0,
    val subjectId: Int? = null,
    val notebookId: Int? = null,
    val userId: Int,
    val groupId: Int? = null,
    val title: String,
    val content: String,
    val isShared: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

@Entity
data class CalendarEvent(
    @PrimaryKey(autoGenerate = true) val eventId: Int = 0,
    val userId: Int,
    val groupId: Int,
    val eventName: String,
    val eventType: String,
    val eventLocation: String,
    val eventDescription: String,
    val eventDate: Long,
    val eventTime: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class GroupEntity(
    @PrimaryKey(autoGenerate = true) val groupId: Int = 0,
    val groupName: String,
    val subjectId: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class GroupMember(
    @PrimaryKey(autoGenerate = true) val groupMemberId: Int = 0,
    val groupId: Int,
    val userId: Int,
    val contributionPercentage: Float,
    val joinedAt: Long,
    val updatedAt: Long? = null
)

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true) val taskId: Int = 0,
    val groupId: Int,
    val assignedTo: Int,
    val taskName: String,
    val isCompleted: Boolean = false,
    val dueDate: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class PeerRating(
    @PrimaryKey(autoGenerate = true) val ratingId: Int = 0,
    val groupId: Int,
    val reviewerId: Int,
    val revieweeId: Int,
    val ratingScore: Int,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class Feedback(
    @PrimaryKey(autoGenerate = true) val feedbackId: Int = 0,
    val ratingId: Int,
    val feedbackText: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class Reflection(
    @PrimaryKey(autoGenerate = true) val reflectionId: Int = 0,
    val userId: Int,
    val groupId: Int,
    val reflectionText: String,
    val projectCompleted: Boolean = false,
    val submittedAt: Long? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class Alert(
    @PrimaryKey(autoGenerate = true) val alertId: Int = 0,
    val groupId: Int,
    val userId: Int,
    val alertType: String,
    val alertMessage: String,
    val isResolved: Boolean = false,
    val createdAt: Long = System.currentTimeMillis(),
    val resolvedAt: Long? = null
)
//Add the lectures lecture Dates entity
@Entity
data class Lecture(
    @PrimaryKey(autoGenerate = true) val lectureId: Int = 0,
    val userId: Int,
    val subjectId: Int,
    val lectureTitle: String,
    val audioMp3Path: String? = null,
    val transcribedText: String? = null,
    val formattedNotes: String? = null,
    val createdAt: Long  = System.currentTimeMillis()
)

@Entity
data class LectureTimestamp(
    @PrimaryKey(autoGenerate = true) val timestampId: Int = 0,
    val lectureId: Int,
    val userId: Int,
    val timeMarker: Long,
    val timestampLabel: String,
    val createdSt: Long = System.currentTimeMillis()
)

@Entity
data class Summary(
    @PrimaryKey(autoGenerate = true) val summaryId: Int = 0,
    val userId: Int,
    val lectureId: Int,
    val noteId: Int,
    val originalText: String,
    val summarisedText: String,
    val isJargonFree: Boolean,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)

@Entity
data class Translation(
    @PrimaryKey(autoGenerate = true) val translationId: Int = 0,
    val userId: Int,
    val noteId: Int,
    val lectureId: Int,
    val originalText: String,
    val translatedText: String,
    val sourceLanguage: String,
    val targetLanguage: String,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long? = null
)