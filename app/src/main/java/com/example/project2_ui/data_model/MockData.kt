package com.example.project2_ui.data_model

import com.example.project2_ui.data_model.*

object MockData {

    val notes = listOf(
        Note(
            id = "n1",
            title = "Database Normalisation",
            course = "Database Systems",
            topic = "1NF, 2NF, 3NF",
            content = "A long explanation of database normalisation, covering repeating groups, partial dependencies, and transitive dependencies with worked examples.",
            tags = listOf("Database", "Revision"),
            dateUpdated = "Updated yesterday",
            isFavorite = true,
            isReviewed = false
        ),
        Note(
            id = "n2",
            title = "Java Classes",
            course = "Application Development",
            topic = "OOP Basics",
            content = "Notes on classes, objects, constructors, and encapsulation in Java.",
            tags = listOf("Programming"),
            dateUpdated = "Updated 2 days ago",
            isFavorite = false,
            isReviewed = true
        ),
        Note(
            id = "n3",
            title = "OSI Model",
            course = "Networking",
            topic = "7 Layers",
            content = "Overview of the 7 layers of the OSI model and what each layer is responsible for.",
            tags = listOf("Networking", "Revision"),
            dateUpdated = "Updated 5 days ago",
            isFavorite = false,
            isReviewed = false
        )
    )

    val courseFilters = listOf("All", "Programming", "Networking", "Database")

    val moduleProgress = listOf(
        ModuleProgress("Application Development", 80, ModuleStatus.ON_TRACK),
        ModuleProgress("Database Systems", 65, ModuleStatus.IN_PROGRESS),
        ModuleProgress("Networking", 45, ModuleStatus.NEEDS_ATTENTION)
    )

    val overallProgressPercent = 72

    val weeklyStats = WeeklyStats(
        studySessions = 8,
        notesCreated = 6,
        tasksCompleted = 4,
        hoursStudied = 8,
        hoursGoal = 10
    )

    val deadlines = listOf(
        Deadline("Database Assignment", "Due Friday", 80),
        Deadline("Programming Test", "Next Tuesday", 45)
    )

    val studyGoals = listOf(
        StudyGoal("Study for 10 hours this week", 80),
        StudyGoal("Complete Chapter 5", 70)
    )

    val attentionItems = listOf(
        AttentionItem("Networking", "3 topics remaining"),
        AttentionItem("Database", "Assignment due Friday")
    )
}