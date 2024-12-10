package com.example.boatmatea.ui.navigation

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.boatmatea.ui.home.HomeScreen
import com.example.boatmatea.ui.log.LogBookScreen
import com.example.boatmatea.ui.theme.BoatMateATheme
import com.example.boatmatea.ui.theme.LightColorScheme

@Composable
fun AppBottomBarMaterial3(navController: NavHostController) {
    BottomAppBar(
        modifier = Modifier
            .height(48.dp),
        containerColor = LightColorScheme.tertiary,
        actions = {
            IconButton(modifier = Modifier.weight(0.33F),
                onClick = {
                    navController.navigate(NavDestination.Home(name = "FromBottomBar"))
                }) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Menu")
            }
            IconButton(modifier = Modifier.weight(0.33F),
                onClick = {
                    navController.navigate(NavDestination.LogBook(foo = 282))
                }) {
                Icon(imageVector = Icons.Filled.Build, contentDescription = "Learn")
            }
            IconButton(modifier = Modifier.weight(0.33F),
                onClick = {
                    navController.navigate(NavDestination.Learn(id = "FromBottomBar"))
                }) {
                Icon(imageVector = Icons.Outlined.Star, contentDescription = null)
            }
        })
}

@Composable
fun NavigationGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavDestination.Home(name = "START_FROM_BOTTOM"),
        builder = {
            composable<NavDestination.Home> {
                HomeScreen(
                    modifier = modifier,
                )
            }
            composable<NavDestination.LogBook> { backStackEntry ->
                val learn: NavDestination.LogBook = backStackEntry.toRoute()
                LogBookScreen()
//                LearnScreen(
//                    modifier = modifier,
//                    navController = navController,
//                    foo = learn.foo
//                )
            }
            composable<NavDestination.Learn> { backStackEntry ->
                val profile: NavDestination.Learn = backStackEntry.toRoute()
                HomeScreen(
                    modifier = modifier,
                )
//                ProfileScreen(
//                    modifier = modifier,
//                    navController = navController,
//                    id = profile.id
//                )
            }
        })
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NavigationPreview() {
    BoatMateATheme {
        NavigationGraph(navController = rememberNavController())
    }
}