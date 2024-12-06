package com.example.boatmatea.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LogEntryRepository(private val database: LogEntryDatabase) {
    suspend fun getAllEntries(): Flow<List<LogEntry>> {
        return withContext(Dispatchers.IO) {
            database.dao().getAll()
        }
    }

    suspend fun insert(entry: LogEntry) {
        database.dao().insert(entry)
    }

    suspend fun deleteAll() {
        database.dao().deleteAll()
    }
}