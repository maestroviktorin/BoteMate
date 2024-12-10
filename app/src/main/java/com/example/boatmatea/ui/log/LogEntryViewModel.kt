import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.boatmatea.data.LogEntry
import com.example.boatmatea.data.LogEntryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LogEntryViewModel(private val repository: LogEntryRepository) : ViewModel() {

    private val _logEntries = MutableStateFlow<List<LogEntry>>(emptyList())
    val logEntries: StateFlow<List<LogEntry>> = _logEntries

    init {
        viewModelScope.launch {
            repository.getAllLogEntries().collect { entries ->
                _logEntries.value = entries
            }
        }
    }

    fun insertLogEntry(logEntry: LogEntry) {
        viewModelScope.launch {
            repository.insertLogEntry(logEntry)
        }
    }

    fun deleteLogEntry(logEntry: LogEntry) {
        viewModelScope.launch {
            repository.deleteLogEntry(logEntry)
        }
    }
}