package org.sopt.at.data.service

import kotlinx.serialization.json.JsonElement
import org.sopt.at.data.dto.BaseResponse
import org.sopt.at.data.dto.request.NicknameChangeRequestDto
import org.sopt.at.data.dto.response.NicknameResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface UserService {
    @GET("/api/v1/users")
    suspend fun getNicknameList(@Query("keyword") keyword: String): BaseResponse<NicknameResponseDto>

    @PATCH("/api/v1/users")
    suspend fun changeNickname(
        @Header("userId") userId: Long,
        @Body request: NicknameChangeRequestDto
    ): BaseResponse<JsonElement>

}