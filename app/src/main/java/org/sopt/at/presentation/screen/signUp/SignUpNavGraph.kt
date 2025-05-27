package org.sopt.at.presentation.screen.signUp

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.sopt.at.presentation.main.AppRoute
import org.sopt.at.presentation.screen.signUp.SignUpPwScreen

@Composable
fun SignUpNavGraph(
    navController: NavHostController,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    NavHost(navController, startDestination = SignUpRoute.Id.route) {
        composable(SignUpRoute.Id.route) {
            SignUpIdScreen(
                id = viewModel.id,
                isError = viewModel.isIdError,
                onIdChange = { viewModel.id = it },
                onNext = {
                    if (viewModel.validateId()) {
                        navController.navigate(SignUpRoute.Pw.route)
                    }
                }
            )
        }
        composable(SignUpRoute.Pw.route) {
            SignUpPwScreen(
                pw = viewModel.pw,
                isError = viewModel.isPwError,
                pwVisible = viewModel.pwVisible,
                onPwChange = { viewModel.pw = it} ,
                onTogglePwVisible = {viewModel.pwVisible = !viewModel.pwVisible},
                onNext2 = {
                    if (viewModel.validatePw()) {
                        navController.navigate(AppRoute.SignIn.route)
                    }
                }
            )
        }
        composable(SignUpRoute.Nickname.route) {  }
    }
}
