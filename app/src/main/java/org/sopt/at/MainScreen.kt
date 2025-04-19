package org.sopt.at

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen() }
            composable("shorts") { ShortsScreen() }
            composable("live") { LiveScreen() }
            composable("search") { SearchScreen() }
            composable("history") { HistoryScreen() }

        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview6() {
    ATSOPTANDROIDTheme {
        Greeting2("Android")
    }
}