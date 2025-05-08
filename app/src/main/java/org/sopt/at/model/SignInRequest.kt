package org.sopt.at.model

data class SignInRequest(
    val loginId: String,
    val password: String,
)

data class SignInResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignInData?
)

data class SignInData(
    val userId: Int,
)
