package org.sopt.at.data.api

import org.sopt.at.data.dto.request.SignInRequest
import org.sopt.at.data.dto.request.SignInResponse
import org.sopt.at.data.dto.request.SignUpRequest
import org.sopt.at.data.dto.request.SignUpResponse
import org.sopt.at.data.dto.response.UserInfoResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("/api/v1/auth/signin")
    fun signIn(@Body request: SignInRequest): Call<SignInResponse>

    @GET("/api/v1/users/me")
    fun getUserInfo(@Header("userId") userId: Long) : Call<UserInfoResponse>

}