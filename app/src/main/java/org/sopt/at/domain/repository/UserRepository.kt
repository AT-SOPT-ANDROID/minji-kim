package org.sopt.at.domain.repository

import org.sopt.at.data.dto.request.SignInRequestDto
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.data.service.AuthService
import org.sopt.at.data.service.UserService
import org.sopt.at.domain.model.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val authService: AuthService,
    private val userService: UserService
) {
    suspend fun signIn(request: SignInRequestDto): Result<UserEntity> {
        return try {
            val response = authService.signIn(request)
            if (response.success && response.data != null) {
                val userId = response.data.userId
                Result.success(UserEntity(userId))
            } else {
                Result.failure(Throwable(response.message ?: "로그인 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(request: SignUpRequestDto): Result<UserEntity> {
        return try {
            val response = authService.signUp(request)
            if (response.success && response.data != null) {
                val userId = response.data.userId
                val nickname = response.data.nickname
                Result.success(UserEntity(userId, nickname))
            } else {
                Result.failure(Throwable(response.message ?: "회원가입 실패"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}