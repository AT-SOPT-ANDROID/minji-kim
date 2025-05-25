package org.sopt.at.data.dto.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName


@Serializable
data class SignUpResponseDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("nickname")
    val nickname: String
)