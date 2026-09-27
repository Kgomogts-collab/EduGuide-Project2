package com.example.project2database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//ROOM SQL databse

@Database(
    entities = [
        User::class,
        Subject::class,
        Notebook::class,
        Note::class,
        CalendarEvent::class,
        GroupEntity::class,
        GroupMember::class,
        TaskEntity::class,
        PeerRating::class,
        Feedback::class,
        Reflection::class,
        Alert::class,
        Lecture::class,
        LectureTimestamp::class,
        Summary::class,
        Translation::class

    ],
    version = 1,
    exportSchema = false
)
abstract class ProjectDatabase: RoomDatabase() {
    abstract fun dao(): DAO

    companion object {
        // @Volatile ensures changes made by one thread are instantly visible to others
        @Volatile
        private var INSTANCE: ProjectDatabase? = null

        fun getDatabase(context: Context): ProjectDatabase {
            // If the instance is not null, return it; otherwise, create it safely
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ProjectDatabase::class.java,
                    "project_database.db"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }
}


