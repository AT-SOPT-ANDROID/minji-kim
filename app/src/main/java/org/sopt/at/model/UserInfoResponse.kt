package org.sopt.at.model

data class UserInfoResponse(
    val success: Boolean,
    val message: String,
    val data: UserInfo
)

data class UserInfo(
    val nickname: String
)

