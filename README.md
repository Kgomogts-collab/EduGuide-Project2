An academic support system designed to improve collaboration, learning, and course management within an educational environment — covering study notes, real-time class support, group project tracking, and progress monitoring.

This repository contains two parts of the system, built by different team members:
UI layer — Android app screens (Login & Signup, Profile, AI Summary, Dashboard, Calendar, Group Collaborate, Notes, Progress) built in Kotlin with Jetpack Compose
Database layer — Room persistence layer (Entities, DAO, Database setup) modelling the full system's data

These two parts currently live in separate packages (com.example.project2_ui and com.example.project2database.database) and are not yet wired together. The UI currently runs on sample/mock data; the database layer defines the real persistence structure but isn't yet connected to the screens. Wiring them together is a planned next step.

Tech stack:
Language: Kotlin
UI Framework: Jetpack Compose
Navigation: Navigation Compose
Persistence: Room 

Database layer
Full Room schema covering: User, Subject, Notebook, Note, CalendarEvent, GroupEntity, GroupMember, TaskEntity, PeerRating, Reflection, Alert, Lecture, LectureTimestamp, Summary
DAO with Insert/Read/Update/Delete operations for every entity

Building and running
Open the project in Android Studio, let Gradle sync
Select a device/emulator from the toolbar dropdown
Press Run
The app opens on the Notes screen; use the bottom navigation to reach Progress

Known limitation
Database layer is not yet connected to the UI — screens currently use in-memory mock data.
No authentication/login flow implemented yet, despite User entity supporting it.
AI Summary, real-time transcription, and translation are UI-only mockups, not connected to any real API
Package structure between UI and database code should be unified under one consistent root package as the project matures.
