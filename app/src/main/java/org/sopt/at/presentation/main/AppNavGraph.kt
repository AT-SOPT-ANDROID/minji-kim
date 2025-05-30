package org.sopt.at.presentation.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.screen.history.HistoryScreen
import org.sopt.at.presentation.screen.home.HomeScreen
import org.sopt.at.presentation.screen.live.LiveScreen
import org.sopt.at.presentation.screen.my.MyScreen
import org.sopt.at.presentation.screen.nickname.NicknameScreen
import org.sopt.at.presentation.screen.search.SearchScreen
import org.sopt.at.presentation.screen.shorts.ShortsScreen
import org.sopt.at.presentation.screen.signIn.SignInScreen
import org.sopt.at.presentation.screen.signUp.SignUpIdScreen
import org.sopt.at.presentation.screen.signUp.SignUpNicknameScreen
import org.sopt.at.presentation.screen.signUp.SignUpPwScreen
import org.sopt.at.presentation.screen.signUp.SignUpViewModel

@Composable
fun AppNavGraph(navController: NavHostController, viewModel: SignUpViewModel = hiltViewModel()) {

    NavHost(navController, startDestination = AppRoute.SignIn.route) {

        composable(AppRoute.Main.route) {
            MainScreen(navController = navController)
        }

        composable(AppRoute.SignIn.route) {
            SignInScreen(
                onNavigateToSignUp = { navController.navigate(AppRoute.SignUpId.route) },
                onSignInSuccess = { navController.navigate(AppRoute.Main.route) }
            )
        }

        composable(AppRoute.SignUpId.route) {
            SignUpIdScreen(
                id = viewModel.id,
                isError = viewModel.isIdError,
                onIdChange = { viewModel.id = it },
                onNext = {
                    if (viewModel.validateId()) {
                        navController.navigate(AppRoute.SignUpPw.route)
                    }
                }
            )
        }
        composable(AppRoute.SignUpPw.route) {
            SignUpPwScreen(
                pw = viewModel.pw,
                isError = viewModel.isPwError,
                pwVisible = viewModel.pwVisible,
                onPwChange = { viewModel.pw = it} ,
                onTogglePwVisible = {viewModel.pwVisible = !viewModel.pwVisible},
                onNext2 = {
                    if (viewModel.validatePw()) {
                        navController.navigate(AppRoute.SignUpNickname.route)
                    }
                }
            )
        }
        composable(AppRoute.SignUpNickname.route) {
            val context = LocalContext.current
            LaunchedEffect(viewModel.signUpResult) {
                if (viewModel.signUpResult?.isSuccess == true) {
                    navController.navigate(AppRoute.SignIn.route)
                }
            }
            SignUpNicknameScreen(
                nickName = viewModel.nickName,
                isError = viewModel.isNickNameError,
                onNickNameChange = { viewModel.nickName = it },
                onComplete = {
                    viewModel.finishSignUp(context)
                }
            )
        }
    }
}


