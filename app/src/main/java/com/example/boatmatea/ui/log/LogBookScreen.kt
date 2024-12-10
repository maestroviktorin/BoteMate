package com.example.boatmatea.ui.log

import LogEntryViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boatmatea.data.DeviceType
import com.example.boatmatea.data.LogEntry
import com.example.boatmatea.data.LogEntryType
import com.example.boatmatea.ui.AppViewModelProvider
import com.example.boatmatea.ui.theme.LightColorScheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun LogBookScreen(

) {
    val viewModel: LogEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
    val logEntries by viewModel.logEntries.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Entry")
            }
        },
        containerColor = LightColorScheme.primary,
        modifier = Modifier
            .padding(bottom = 40.dp)
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(LightColorScheme.secondary)
        ) {
            items(logEntries) { entry ->
                LogEntryItem(
                    entry = entry,
                    onDelete = { logEntry ->
                        viewModel.deleteLogEntry(logEntry)
                    }
                )
            }
        }

        if (showAddDialog) {
            AddLogEntryDialog(
                onDismiss = { showAddDialog = false },
                onSave = { newEntry ->
                    viewModel.insertLogEntry(newEntry)
                }
            )
        }
    }
}

@Composable
fun LogEntryItem(
    entry: LogEntry,
    onDelete: (LogEntry) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .background(Color.White)
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "Дата: ${entry.date}", fontFamily = FontFamily.Monospace)
                Text(
                    text = "Тип записи: ${entry.logEntryType.name}",
                    fontFamily = FontFamily.Monospace
                )
                Text(
                    text = "Устройство: ${entry.deviceType.name}",
                    fontFamily = FontFamily.Monospace
                )
                if (entry.message != null && entry.message != "") {
                    Text(text = "Комментарий: ${entry.message}", fontFamily = FontFamily.Monospace)
                }
            }

            // Delete Button
            IconButton(
                onClick = { onDelete(entry) }
            ) {
                Icon(
                    Icons.Filled.Delete,
                    contentDescription = "Delete Entry"
                )
            }
        }
    }
}

@Composable
fun AddLogEntryDialog(
    onDismiss: () -> Unit,
    onSave: (LogEntry) -> Unit
) {
    var selectedDate by remember { mutableStateOf(LocalDate.now()) }
    var openDatePicker by remember { mutableStateOf(false) }
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val formattedDate = selectedDate.format(formatter)

    var logEntryType by remember { mutableStateOf(LogEntryType.CONSUMABLE) }
    var deviceType by remember { mutableStateOf(DeviceType.ANCHOR) }
    var message by remember { mutableStateOf("") }

    var expandedLogType by remember { mutableStateOf(false) }
    var expandedDeviceType by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Добавить запись") },
        text = {
            Column {
                // Date TextField
                OutlinedTextField(
                    value = formattedDate,
                    onValueChange = {},
                    label = { Text("Дата") },
                    trailingIcon = {
                        Icon(
                            Icons.Filled.DateRange,
                            contentDescription = "Open Calendar",
                            modifier = Modifier.clickable { openDatePicker = true }
                        )
                    }
                )
                if (openDatePicker) {
                    CustomDatePicker(
                        initialSelectedDate = selectedDate,
                        onDateSelected = { newDate ->
                            selectedDate = newDate
                            openDatePicker = false
                        },
                        onDismissRequest = { openDatePicker = false }
                    )
                }

                Box {
                    // Log Entry Type Dropdown
                    OutlinedTextField(
                        value = logEntryType.name,
                        onValueChange = {}, // Value change handled via dropdown
                        label = { Text("Тип записи") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                contentDescription = "Expand",
                                modifier = Modifier.clickable { expandedLogType = true }
                            )
                        }
                    )
                    DropdownMenu(
                        expanded = expandedLogType,
                        onDismissRequest = { expandedLogType = false },
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        LogEntryType.entries.forEach { type ->
                            DropdownMenuItem(
                                onClick = {
                                    logEntryType = type
                                    expandedLogType = false
                                },
                                text = { Text(type.name) }
                            )
                        }
                    }
                }

                Box {
                    // Device Type Dropdown
                    OutlinedTextField(
                        value = deviceType.name,
                        onValueChange = {}, // Value change handled via dropdown
                        label = { Text("Устройство") },
                        trailingIcon = {
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                contentDescription = "Expand",
                                modifier = Modifier.clickable { expandedDeviceType = true }
                            )
                        }
                    )
                    DropdownMenu(
                        expanded = expandedDeviceType,
                        onDismissRequest = { expandedDeviceType = false },
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                    ) {
                        DeviceType.entries.forEach { type ->
                            DropdownMenuItem(
                                onClick = {
                                    deviceType = type
                                    expandedDeviceType = false
                                },
                                text = { Text(type.name) }
                            )
                        }
                    }
                }

                // Message TextField
                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    label = { Text("Комментарий") }
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    val newEntry = LogEntry(
                        date = selectedDate,
                        logEntryType = logEntryType,
                        deviceType = deviceType,
                        message = message
                    )
                    onSave(newEntry)
                    onDismiss()
                }
            ) {
                Text("Записать", color = LightColorScheme.secondary)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Назад", color = LightColorScheme.primary)
            }
        }
    )
}

@Composable
fun CustomDatePicker(
    initialSelectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit,
    onDismissRequest: () -> Unit
) {
    var selectedDate by remember { mutableStateOf(initialSelectedDate) }

    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .background(Color.White)
                .fillMaxWidth()
        ) {
            Text(
                text = "Select Date",
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Date Selection UI (e.g., buttons for year, month, day)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    selectedDate = selectedDate.minusDays(1)
                }) {
                    Text("-")
                }
                Text(selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                Button(onClick = {
                    selectedDate = selectedDate.plusDays(1)
                }) {
                    Text("+")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = { onDismissRequest() }) {
                    Text("Cancel")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    onDateSelected(selectedDate)
                    onDismissRequest()
                }) {
                    Text("OK")
                }
            }
        }
    }
}