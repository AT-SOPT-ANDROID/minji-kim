package org.sopt.at.data.dto.response

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import org.sopt.at.domain.model.UserEntity

@Serializable
data class SignUpResponseDto(
    @SerialName("userId")
    val userId: Int,
    @SerialName("nickname")
    val nickname: String
) {
    fun SignUpResponseDto.toEntity(): UserEntity {
        return UserEntity(
            userId = userId,
            nickname = nickname
        )
    }
}