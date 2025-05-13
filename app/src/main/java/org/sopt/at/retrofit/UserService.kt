package org.sopt.at.retrofit

import org.sopt.at.model.BaseResponse
import org.sopt.at.model.NicknameChangeRequest
import org.sopt.at.model.NicknameCheckResponse
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
