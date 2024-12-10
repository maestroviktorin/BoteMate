package com.example.boatmatea.ui.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class NavDestination {
    @Serializable
    data class Home(val name: String) : NavDestination()

    @Serializable
    data class LogBook(val foo: Int) : NavDestination()

    @Serializable
    data class Learn(val id: String) : NavDestination()
}