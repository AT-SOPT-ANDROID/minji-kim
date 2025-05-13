package org.sopt.at.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import org.sopt.at.ui.home.HomeScreen
import org.sopt.at.ui.my.NicknameScreen
import org.sopt.at.ui.history.HistoryScreen
import org.sopt.at.ui.live.LiveScreen
import org.sopt.at.ui.my.MyScreen
import org.sopt.at.ui.search.SearchScreen
import org.sopt.at.ui.shorts.ShortsScreen


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


