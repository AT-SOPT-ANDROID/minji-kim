package org.sopt.at.data.dto

data class BaseResponse<T>(
    val success: Boolean,
    val code: String,
    val message: String,
    val data: T?
)