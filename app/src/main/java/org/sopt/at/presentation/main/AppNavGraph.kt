package org.sopt.at.presentation.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.screen.signIn.SignInScreen
import org.sopt.at.presentation.screen.signUp.SignUpNavGraph

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController, startDestination = AppRoute.SignIn.route) {
        composable(AppRoute.SignIn.route) {
            SignInScreen(
                navController = navController,
                onNavigateToSignUp = { navController.navigate(AppRoute.SignUp.route) },
                onSignInSuccess = { navController.navigate(AppRoute.Main.route) }
            )
        }

        composable(AppRoute.SignUp.route) {
            SignUpNavGraph(navController)
        }

        composable(AppRoute.Main.route) {
            BottomNavGraph(navController)
        }
    }
}


