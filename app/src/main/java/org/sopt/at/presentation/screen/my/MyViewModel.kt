package org.sopt.at.presentation.screen.my

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.sopt.at.data.local.UserPreferences
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val userPreferences: UserPreferences,
    private val userRepository: UserRepository
): ViewModel() {
    var nickname by mutableStateOf("")
        private set

    fun fetchNickname() {
        viewModelScope.launch {
            val userId = userPreferences.userIdFlow.first()
            Log.d("MyViewModel", "🔍 userId = $userId")

            if (userId == -1L) {
                nickname = "유저 정보 없음"
                return@launch
            }
            val result = userRepository.getUser(userId.toLong())
            nickname = result.getOrNull()?.nickname ?: "불러오기 실패"
            Log.d("MyViewModel", "🎯 유저 닉네임 = ${result.getOrNull()?.nickname}")
        }
    }
}
