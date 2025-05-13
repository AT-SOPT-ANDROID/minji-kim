package org.sopt.at.data.dto.request

data class NicknameChangeRequest(val nickname: String)

data class BaseResponse(
    val success: Boolean,
    val code: String,
    val message: String
)
