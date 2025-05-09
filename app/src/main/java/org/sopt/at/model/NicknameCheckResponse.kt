package org.sopt.at.model

data class NicknameCheckResponse(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: NicknameData
)

data class NicknameData(
    val nicknameList: List<String>
)
