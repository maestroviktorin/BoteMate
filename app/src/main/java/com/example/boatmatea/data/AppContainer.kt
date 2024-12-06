package com.example.boatmatea.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

/**
 * App container for Dependency injection.
 */
interface AppContainer {
    val repository: LogEntryRepository
}

/**
 * [AppContainer] implementation that provides instance of [OfflineItemsRepository]
 */
class AppDataContainer(private val context: Context) : AppContainer {
    /**
     * Implementation for [LogEntryRepository]
     */
    override val repository: LogEntryRepository by lazy {
        LogEntryRepository(
            LogEntryDatabase.getDatabase(
                context,
                coroutineScope = CoroutineScope(Dispatchers.Default)
            )
        )
    }
}