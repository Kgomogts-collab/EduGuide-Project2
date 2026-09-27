package com.example.project2database.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {

    //---------------------------------------User table
    @Insert
    fun createUser(user: User)

    @Query("Select * FROM User WHERE userId =:userId")
    fun readUser(userId: Int): User

    @Update
    fun updateUser(user:User)

    @Delete
    fun deleteUser(user: User)

    //---------------------------------------Subject
    @Insert
    fun createSubject(subject: Subject)

    @Query("Select * FROM Subject WHERE subjectId =:subjectId")
    fun readSubject(subjectId: Int): Subject

    @Update
    fun updateSubject(subject: Subject)

    @Delete
    fun deleteSubject(subject: Subject)

    //---------------------------------------Notebook
    @Insert
    fun createNotebook(noteBook: Notebook)

    @Query("Select * FROM Notebook WHERE notebookId =:notebookId")
    fun readNotebook(notebookId:Int): Notebook

    @Update
    fun updateNotebook(notebook: Notebook)

    @Delete
    fun deleteNotebook(notebook: Notebook)
    //---------------------------------------Note
    @Insert
    fun createNote(note: Note)

    @Query("Select * FROM Note WHERE noteId =:noteId")
    fun readNote(noteId: Int): Note

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)
    //---------------------------------------Calendar Event
    @Insert
    fun createCalendarEvent(calendarEvent: CalendarEvent)

    @Query("Select * FROM CalendarEvent WHERE eventId =:eventId")
    fun readCalendarEvent(eventId: Int): CalendarEvent

    @Update
    fun updateCalendarEvent(calendarEvent: CalendarEvent)

    @Delete
    fun deleteCalendarEvent(calendarEvent: CalendarEvent)
    //---------------------------------------Group
    @Insert
    fun createGroup(group: GroupEntity)

    @Query("Select * FROM GroupEntity WHERE groupId =:groupId")
    fun readGroupEntity(groupId: Int): GroupEntity

    @Update
    fun updateGroupEntity(group: GroupEntity)

    @Delete
    fun deleteGroupEntity(group: GroupEntity)
    //---------------------------------------GroupMember
    @Insert
    fun createGroupMember(groupMember: GroupMember)

    @Query("Select * FROM GroupMember WHERE userId =:userId")
    fun readGroupMember(userId: Int): GroupMember

    @Update
    fun updateGroupMember(groupMember: GroupMember)

    @Delete
    fun deleteGroupMember(groupMember: GroupMember)
    //---------------------------------------Task Entity
    @Insert
    fun createTaskEntity(taskEntity: TaskEntity)

    @Query("Select * FROM TaskEntity WHERE taskId =:taskId")
    fun readTaskEntity(taskId: Int): TaskEntity

    @Update
    fun updateTaskEntity(taskEntity: TaskEntity)

    @Delete
    fun deleteTaskEntity(taskEntity: TaskEntity)
    //---------------------------------------Peer Rating
    @Insert
    fun createPeerRating(peerRating: PeerRating)

    @Query("Select * FROM PeerRating WHERE ratingId =:ratingId")
    fun readPeerRating(ratingId: Int): PeerRating

    @Update
    fun updatePeerRating(peerRating: PeerRating)

    @Delete
    fun deletePeerRating(peerRating: PeerRating)
    //---------------------------------------Feedback
    @Insert
    fun createFeedback(feedback: Feedback)

    @Query("Select * FROM Feedback WHERE feedbackId =:feedbackId")
    fun readFeedback(feedbackId: Int): Feedback

    @Update
    fun updateFeedback(feedback: Feedback)

    @Delete
    fun deleteFeedback(feedback: Feedback)
    //---------------------------------------Reflection
    @Insert
    fun createReflection(reflection: Reflection)

    @Query("Select * FROM Reflection WHERE reflectionId =:reflectionId")
    fun readReflection(reflectionId: Int): Reflection

    @Update
    fun updateReflection(reflection: Reflection)

    @Delete
    fun deleteReflection(reflection: Reflection)

    //---------------------------------------Alert
    @Insert
    fun createAlert(alert: Alert)

    @Query("Select * FROM Alert WHERE alertId =:alertId")
    fun readAlert(alertId: Int): Alert

    @Update
    fun updateAlert(alert: Alert)

    @Delete
    fun deleteAlert(alert: Alert)
    //---------------------------------------Lecture
    @Insert
    fun createLecture(lecture: Lecture)

    @Query("Select * FROM Lecture WHERE lectureId =:lectureId")
    fun readLecture(lectureId: Int): Lecture

    @Update
    fun updateLecture(lecture: Lecture)

    @Delete
    fun deleteLecture(lecture: Lecture)
    //---------------------------------------LectureTimeStamp
    @Insert
    fun createLectureTimeStamp(lectureTimestamp: LectureTimestamp)

    @Query("Select * FROM LectureTimestamp WHERE timestampId =:timestampId")
    fun readLectureTimeStamp(timestampId: Int): LectureTimestamp

    @Update
    fun updateLectureTimeStamp(lectureTimestamp: LectureTimestamp)

    @Delete
    fun deleteLectureTimeStamp(lectureTimestamp: LectureTimestamp)
    //---------------------------------------Summary
    @Insert
    fun createSummary(summary: Summary)

    @Query("Select * FROM Summary WHERE summaryId =:summaryId")
    fun readSummary(summaryId: Int): Summary

    @Update
    fun updateSummary(summary: Summary)

    @Delete
    fun deleteSummary(summary: Summary)
    //---------------------------------------Translation
    @Insert
    fun createTranslation(translation: Translation)

    @Query("Select * FROM Translation WHERE translationId =:translationId")
    fun readTranslation(translationId: Int): Translation

    @Update
    fun updateTranslation(translation: Translation)

    @Delete
    fun deleteTranslation(translation: Translation)

}