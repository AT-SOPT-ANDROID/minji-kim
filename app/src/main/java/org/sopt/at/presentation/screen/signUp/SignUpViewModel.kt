package org.sopt.at.presentation.screen.signUp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.data.dto.request.SignUpRequestDto
import org.sopt.at.domain.model.UserEntity
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class SignUpViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel() {

    var id by mutableStateOf("")
    var pw by mutableStateOf("")
    var nickName by mutableStateOf("")

    var isIdError by mutableStateOf(false)
    var isPwError by mutableStateOf(false)
    var isNickNameError by mutableStateOf(false)
    var pwVisible by mutableStateOf(false)
    var signUpResult by mutableStateOf<Result<UserEntity>?>(null)
        private set

    fun validateId(): Boolean {
        val isLowerOrNum = id.all { it.isLowerCase() || it.isDigit() }
        val validLength = id.length in 8..20
        isIdError = !(isLowerOrNum && validLength)
        return !isIdError
    }

    fun validatePw(): Boolean {
        val isLower = pw.any { it.isLowerCase() }
        val hasDigit = pw.any { it.isDigit() }
        val validLength = pw.length in 8..15
        isPwError = !(isLower && hasDigit && validLength)
        return !isPwError
    }

    fun validateNickName(): Boolean {
        val validLength = nickName.length in 1..20
        val onlyChars = nickName.all { it.isLetterOrDigit() || isKorean(it) }
        isNickNameError = !(validLength && onlyChars)
        return !isNickNameError
    }

    private fun isKorean(char: Char): Boolean {
        return char in '가'..'힣'
    }

    fun finishSignUp(context: Context) {
        if (!validateNickName()) {
            return
        }
        viewModelScope.launch {
            val request = SignUpRequestDto(id, pw, nickName)
            signUpResult = userRepository.signUp(request)
        }
    }
}
