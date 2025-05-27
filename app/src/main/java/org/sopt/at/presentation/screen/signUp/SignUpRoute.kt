package org.sopt.at.presentation.screen.signUp

sealed class SignUpRoute(val route: String) {
    object Id : SignUpRoute("signup/id")
    object Pw : SignUpRoute("signup/pw")
    object Nickname : SignUpRoute("signup/nickname")
}
