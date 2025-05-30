package org.sopt.at.data.dto.response
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class UserInfoResponseDto(
    @SerialName("nickname")
    val nickname: String
)