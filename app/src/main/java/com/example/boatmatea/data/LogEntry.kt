package com.example.boatmatea.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "logbook")
data class LogEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: LocalDate = LocalDate.now(),
    val logEntryType: LogEntryType = LogEntryType.CONSUMABLE,
    val deviceType: DeviceType = DeviceType.ANCHOR,
    val message: String? = null
)

// Type of the Craft Device.
enum class DeviceType {
    ANCHOR,
    RAILING,
    ENGINE
    // Enough for now.
}

// Type of the Log Entry
enum class LogEntryType {
    CONSUMABLE,
    REPAIR,
    MODERNIZATION,
    REMOVAL
}