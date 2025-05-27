package org.sopt.at.presentation.main

sealed class AppRoute(val route: String) {
    object SignIn : AppRoute("signin")
    object SignUp : AppRoute("signup")
    object Main : AppRoute("main")
}
