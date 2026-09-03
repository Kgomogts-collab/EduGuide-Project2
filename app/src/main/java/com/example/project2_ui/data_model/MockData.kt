package com.example.project2_ui.data_model

import com.example.project2_ui.data_model.*

object MockData {

    val notes = listOf(
        Note("n1", "Database Normalisation — Lecture 6", "COS 212",
            "1NF/2NF/3NF worked examples, functional dependency exercise, exam hint at 32:10...",
            "14 Aug", FileType.PDF, true),
        Note("n2", "ERD vs UML — recap discussion", "INF 212",
            "Comparing entity relationships to class diagrams, inheritance in UML for User roles...",
            "13 Aug", FileType.DOCX, true),
        Note("n3", "Business Rules workshop notes", "INF 212",
            "Peer verification rules, orphaned note prevention, AI summary auto-generation...",
            "11 Aug", FileType.PDF, false)
    )

    val transcript = listOf(
        TranscriptLine("00:02", "Lecturer", "Right, let's pick up where we left off with normal forms."),
        TranscriptLine("04:18", "Lecturer", "This next part is examinable — pay attention to the FD notation.", isKeyMoment = true),
        TranscriptLine("09:41", "Student", "Can you re-explain transitive dependency?"),
        TranscriptLine("12:30", "Lecturer", "Good question. Transitive dependency means...", isKeyMoment = true)
    )

    val contributionTasks = listOf(
        ContributionTask("t1", "Design ERD for EDU-GUIDE", Difficulty.HARD, Importance.HIGH, 6.5, true, 28),
        ContributionTask("t2", "Write business rules doc", Difficulty.MEDIUM, Importance.HIGH, 4.0, true, 18),
        ContributionTask("t3", "Wireframe — Figma prototype", Difficulty.MEDIUM, Importance.MEDIUM, 3.0, false, 12),
        ContributionTask("t4", "Proofread final report", Difficulty.EASY, Importance.LOW, 1.5, true, 6)
    )

    val groupMembers = listOf(
        GroupMember("m1", "Person1.", 63, 4.6, isCurrentUser = true),
        GroupMember("m2", "Person2.", 58, 4.2),
        GroupMember("m3", "Person3.", 67, 4.8),
        GroupMember("m4", "Person4.", 12, 2.1) // triggers low-participation flag
    )

    val reflection = ReflectionLogEntry(dueDate = "31 Dec 2024", submitted = false)
}