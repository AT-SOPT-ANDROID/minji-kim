package org.sopt.at.data.dto.response

data class UserInfoResponse(
    val success: Boolean,
    val message: String,
    val data: UserInfo
)

data class UserInfo(
    val nickname: String
)

