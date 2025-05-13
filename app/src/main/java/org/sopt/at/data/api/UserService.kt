package org.sopt.at.data.api

import org.sopt.at.data.dto.request.BaseResponse
import org.sopt.at.data.dto.request.NicknameChangeRequest
import org.sopt.at.data.dto.response.NicknameCheckResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.Query

interface UserService {
    @GET("/api/v1/users")
    fun checkNickname(@Query("keyword") nickname: String): Call<NicknameCheckResponse>

    @PATCH("/api/v1/users")
    fun updateNickname(
        @Header("userId") userId: Long,
        @Body request: NicknameChangeRequest
    ): Call<BaseResponse>

}