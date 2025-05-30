package org.sopt.at.presentation.screen.nickname


import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import org.sopt.at.data.local.UserPreferences
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class NicknameChangeViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var newNickname by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set
    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun onNicknameChange(newValue: String) {
        newNickname = newValue
    }

    suspend fun changeNickname(
        onSuccess: () -> Unit
    ) {
        isLoading = true
        errorMessage = null

        var userId = userPreferences.userIdFlow.first()
        if (userId == -1L) {
            errorMessage = "유저 정보 없음"
            isLoading = false
            return
        }

        val result = userRepository.changeNickname(userId.toLong(), newNickname)
        isLoading = false

        result.onSuccess {
            onSuccess()
        }.onFailure {
            errorMessage = it.message ?: "닉네임 변경 실패"
        }
    }

}