package org.sopt.at.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T>(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: T?
)