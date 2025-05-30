package org.sopt.at.presentation.screen.signIn

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.sopt.at.data.dto.request.SignInRequestDto
import org.sopt.at.data.local.UserPreferences
import org.sopt.at.domain.model.UserEntity
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.presentation.main.MainActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class SignInViewModel  @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
): ViewModel() {

    var loginId by mutableStateOf("")
    var loginPw by mutableStateOf("")
    var loginResult by mutableStateOf<Result<UserEntity>?>(null)
        private set

    var loginSuccess by mutableStateOf(false)
        private set

    fun signIn() {
        viewModelScope.launch {
            val request = SignInRequestDto(loginId, loginPw)
            loginResult = userRepository.signIn(request)
            if (loginResult?.isSuccess == true) {
                val userId = loginResult?.getOrNull()?.userId
                if (userId != null) {
                    userPreferences.saveUserId(userId)
                }
                loginSuccess = true
            } else {
                loginSuccess = false
            }
        }
    }
}