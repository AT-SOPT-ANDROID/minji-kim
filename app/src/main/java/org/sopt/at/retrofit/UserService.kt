package org.sopt.at.retrofit

import org.sopt.at.model.NicknameCheckResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/api/v1/users")
    fun checkNickname(@Query("keyword") nickname: String): Call<NicknameCheckResponse>
}
