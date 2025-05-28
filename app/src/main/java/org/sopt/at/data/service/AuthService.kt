package org.sopt.at.data.service

import org.sopt.at.data.dto.BaseResponse
import org.sopt.at.data.dto.request.SignInRequestDto
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.data.dto.response.SignInResponseDto
import org.sopt.at.data.dto.response.SignUpResponseDto
import org.sopt.at.data.dto.response.UserInfoResponseDto

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {
    @POST("/api/v1/auth/signup")
    suspend fun signUp(@Body request: SignUpRequestDto): BaseResponse<SignUpResponseDto>

    @POST("/api/v1/auth/signin")
    suspend fun signIn(@Body request: SignInRequestDto): BaseResponse<SignInResponseDto>

    @GET("/api/v1/users/me")
    suspend fun getUser(@Header("userId") userId: Long) : BaseResponse<UserInfoResponseDto>

}