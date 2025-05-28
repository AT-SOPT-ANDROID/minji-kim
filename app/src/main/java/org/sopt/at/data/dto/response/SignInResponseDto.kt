package org.sopt.at.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.sopt.at.domain.model.UserEntity


@Serializable
data class SignInResponseDto(
    @SerialName("userId")
    val userId: Int
)
