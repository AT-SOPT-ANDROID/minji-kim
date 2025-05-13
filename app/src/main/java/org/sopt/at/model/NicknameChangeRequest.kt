package org.sopt.at.model

data class NicknameChangeRequest(val nickname: String)

data class BaseResponse(
    val success: Boolean,
    val code: String,
    val message: String
)
