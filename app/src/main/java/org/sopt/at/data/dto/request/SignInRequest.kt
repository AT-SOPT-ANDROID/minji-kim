package org.sopt.at.data.dto.request

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

@Serializable
data class SignInRequest(
    @SerialName("loginId")
    val loginId: String,
    @SerialName("password")
    val password: String,
)
