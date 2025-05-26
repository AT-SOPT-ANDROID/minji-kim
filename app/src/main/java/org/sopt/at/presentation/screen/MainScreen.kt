package org.sopt.at.presentation.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import org.sopt.at.presentation.screen.history.HistoryScreen
import org.sopt.at.presentation.screen.home.HomeScreen
import org.sopt.at.presentation.screen.live.LiveScreen
import org.sopt.at.presentation.screen.my.MyScreen
import org.sopt.at.presentation.screen.nickname.NicknameScreen
import org.sopt.at.presentation.main.BottomNavBar
import org.sopt.at.presentation.screen.search.SearchScreen
import org.sopt.at.presentation.screen.shorts.ShortsScreen


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
            composable("home") { HomeScreen(navController = navController) }
            composable("shorts") { ShortsScreen() }
            composable("live") { LiveScreen() }
            composable("search") { SearchScreen() }
            composable("history") { HistoryScreen() }
            composable("my") {
                MyScreen(navController = navController, viewModel = viewModel())
            }
            composable("changeNickname") {
                NicknameScreen(navController = navController)
            }
            }


        }

    }


