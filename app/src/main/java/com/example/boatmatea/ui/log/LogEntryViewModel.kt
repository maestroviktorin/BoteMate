package com.example.boatmatea.ui.log

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boatmatea.data.LogEntry
import com.example.boatmatea.data.LogEntryRepository
import kotlinx.coroutines.launch

class LogEntryViewModel(private val repository: LogEntryRepository) : ViewModel() {
    var uiState by mutableStateOf(LogEntryUiState())
        private set

    fun updateUiState(logEntry: LogEntry) {
        uiState = LogEntryUiState(logEntry, isValid = validateInput(logEntry))
    }

    private fun validateInput(logEntry: LogEntry = uiState.logEntry): Boolean {
        return true
    }

    val getAllEntries = viewModelScope.launch {
        repository.getAllEntries()
    }

    suspend fun saveItem() {
        if (validateInput()) {
            repository.insert(uiState.logEntry)
        }
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}

data class LogEntryUiState(
    val logEntry: LogEntry = LogEntry(),
    val isValid: Boolean = false
)