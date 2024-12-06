package com.example.boatmatea.ui.log

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.boatmatea.ui.AppViewModelProvider

@Composable
fun LogbookScreen(
    modifier: Modifier = Modifier,
    viewModel: LogEntryViewModel = viewModel(factory = AppViewModelProvider.Factory)
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold { innerPadding ->
        LogEntryBody(modifier.padding(innerPadding), viewModel.uiState)
    }
}

@Composable
fun LogEntryBody(modifier: Modifier = Modifier, uiState: LogEntryUiState) {
    Column {
        uiState.logEntry.message?.let { Text(text = it) }
    }
}