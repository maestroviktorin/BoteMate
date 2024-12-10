package com.example.boatmatea.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate

@Database(
    entities = [LogEntry::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class LogEntryDatabase : RoomDatabase() {
    abstract fun dao(): LogEntryDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LogEntryDatabase? = null

        fun getDatabase(
            context: Context,
            coroutineScope: CoroutineScope
        ): LogEntryDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LogEntryDatabase::class.java,
                    "word_database"
                )
                    .addCallback(LogEntryDatabaseCallback(coroutineScope))
                    .build()

                INSTANCE = instance
                instance
            }
        }
    }

    private class LogEntryDatabaseCallback(
        private val scope: CoroutineScope
    ) : Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.dao())
                }
            }
        }

        suspend fun populateDatabase(logEntryDao: LogEntryDao) {
            // Add sample Log Entries.
            val logEntry = LogEntry(
                date = LocalDate.now(),
                logEntryType = LogEntryType.REPAIR,
                deviceType = DeviceType.ANCHOR,
            )
            logEntryDao.upsertLogEntry(logEntry)

            // TODO: Add your own words!
        }
    }
}