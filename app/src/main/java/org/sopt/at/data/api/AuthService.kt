package org.sopt.at.data.api

import org.sopt.at.data.dto.BaseResponse
import org.sopt.at.data.dto.request.SignInRequest
import org.sopt.at.data.dto.request.SignUpRequest
import org.sopt.at.data.dto.response.SignInResponseDto
import org.sopt.at.data.dto.response.SignUpResponseDto
import org.sopt.at.data.dto.response.UserInfoResponseDto

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    fun signUp(@Body request: SignUpRequest): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    fun signIn(@Body request: SignInRequest): BaseResponse<SignInResponseDto>

    @GET("/api/v1/users/me")
    fun getUserInfo(@Header("userId") userId: Long) : BaseResponse<UserInfoResponseDto>

}