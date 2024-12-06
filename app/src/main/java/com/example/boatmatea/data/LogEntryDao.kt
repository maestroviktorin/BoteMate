package com.example.boatmatea.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface LogEntryDao {
    @Upsert
    suspend fun upsertLogEntry(logEntry: LogEntry)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entry: LogEntry)

    @Query("SELECT * FROM logbook")
    fun getAll(): Flow<List<LogEntry>>

    @Query("DELETE FROM logbook")
    suspend fun deleteAll()
    // TODO: Add @Query methods.
}