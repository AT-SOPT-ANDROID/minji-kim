package org.sopt.at.model

data class SignUpRequest(
    val loginId: String,
    val password: String,
    val nickname: String
)

data class SignUpResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: SignUpUserData?
)

data class SignUpUserData(
    val userId: Int,
    val nickname: String
)