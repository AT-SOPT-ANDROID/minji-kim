package org.sopt.at

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import org.sopt.at.ui.theme.ATSOPTANDROIDTheme
import org.sopt.at.viewmodel.HomeViewModel


@Composable
fun MainScreen(nickname: String) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) }

    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController = navController)}
            composable("shorts") { ShortsScreen() }
            composable("live") { LiveScreen() }
            composable("search") { SearchScreen() }
            composable("history") { HistoryScreen() }
            composable("my") {
                MyScreen(navController = navController, nickname = nickname)
            }
            }


        }

    }


