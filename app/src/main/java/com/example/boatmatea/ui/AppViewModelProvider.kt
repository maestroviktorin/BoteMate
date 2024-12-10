package com.example.boatmatea.ui

import LogEntryViewModel
import android.app.Application
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.boatmatea.BoatMateApplication

/**
 * Provides Factory to create instance of ViewModel for the entire Inventory app
 */
object AppViewModelProvider {
    val Factory = viewModelFactory {
        // Initializer for LogEntryViewModel
        initializer {
            LogEntryViewModel(
                boatmateApplication().container.repository
            )
        }

    }
}

/**
 * Extension function to queries for [Application] object and returns an instance of
 * [BoatMateApplication].
 */
fun CreationExtras.boatmateApplication(): BoatMateApplication =
    (this[AndroidViewModelFactory.APPLICATION_KEY] as BoatMateApplication)