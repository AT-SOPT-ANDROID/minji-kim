package org.sopt.at.presentation.main

sealed class AppRoute(val route: String) {
    object SignIn : AppRoute("signin")
    object SignUpId : AppRoute("signup_id")
    object SignUpPw : AppRoute("signup_pw")
    object SignUpNickname : AppRoute("signup_nickname")
    object Main : AppRoute("main")
}
