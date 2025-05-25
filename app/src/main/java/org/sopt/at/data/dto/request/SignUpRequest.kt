package org.sopt.at.data.dto.request
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SignUpRequest(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
    @SerialName("nickname")
    val nickname: String
)