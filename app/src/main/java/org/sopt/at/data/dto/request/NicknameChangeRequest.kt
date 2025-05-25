package org.sopt.at.data.dto.request
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NicknameChangeRequestDto(
    @SerialName("nickname")
    val nickname: String
)
