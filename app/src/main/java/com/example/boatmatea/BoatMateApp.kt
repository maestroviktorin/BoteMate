package com.example.boatmatea

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.boatmatea.ui.navigation.NavigationGraph

@Composable
fun BoatMateApp(modifier: Modifier = Modifier) {
    NavigationGraph(modifier, navController = rememberNavController())
}