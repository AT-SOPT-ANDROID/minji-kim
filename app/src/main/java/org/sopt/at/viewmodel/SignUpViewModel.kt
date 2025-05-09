package org.sopt.at.viewmodel

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.sopt.at.retrofit.RetrofitInstance
import org.sopt.at.SignUpNickname
import org.sopt.at.SignInActivity
import org.sopt.at.model.NicknameCheckResponse

import org.sopt.at.model.SignUpRequest
import org.sopt.at.model.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {

    var id by mutableStateOf("")
    var pw by mutableStateOf("")
    var nickName by mutableStateOf("")
    var isIdError by mutableStateOf(false)
    var isPwError by mutableStateOf(false)
    var isNickNameError by mutableStateOf(false)
    var pwVisible by mutableStateOf(false)
    var screenState by mutableStateOf(SignUpScreenState.ID)

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

    fun checkNickNameDuplication(nickname: String, onResult: (Boolean) -> Unit) {
        RetrofitInstance.userService.checkNickname(nickname)
            .enqueue(object : Callback<NicknameCheckResponse> {
                override fun onResponse(
                    call: Call<NicknameCheckResponse>,
                    response: Response<NicknameCheckResponse>
                ) {
                    val list = response.body()?.data?.nicknameList ?: emptyList()
                    val isDuplicated = nickname in list
                    onResult(!isDuplicated)
                }

                override fun onFailure(call: Call<NicknameCheckResponse>, t: Throwable) {
                    onResult(false)
                }
            })
    }

    private fun isKorean(char: Char): Boolean {
        return char in '가'..'힣'
    }

    fun goToPwScreen() {
        if (validateId()) screenState = SignUpScreenState.PASSWORD
    }

    fun goToNickName() {
        if (validatePw()) screenState = SignUpScreenState.NICKNAME
    }

    fun finishSignUp(context: Context) {
        if (!validateNickName()) {
            Toast.makeText(context, "닉네임 조건에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
            return
        }

        checkNickNameDuplication(nickName) { isAvailable ->
            if (!isAvailable) {
                Toast.makeText(context, "이미 존재하는 닉네임입니다.", Toast.LENGTH_SHORT).show()
                return@checkNickNameDuplication
            }

            val request = SignUpRequest(id, pw, nickName)

            RetrofitInstance.authService.signUp(request)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body?.success == true) {
                                Toast.makeText(context, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                                val intent = Intent(context, SignInActivity::class.java)
                                intent.putExtra("nickname", nickName)
                                context.startActivity(intent)
                            } else {
                                Toast.makeText(
                                    context,
                                    body?.message ?: "서버 응답 오류",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        } else {
                            val errorBody = response.errorBody()?.string() ?: "내용 없음"
                            Log.e("SIGNUP_ERROR", "HTTP 오류: ${response.code()}, error: $errorBody")

                            Toast.makeText(
                                context,
                                "HTTP 오류: ${response.code()}\n$errorBody",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Log.e("SIGNUP_ERROR", "네트워크 오류: ${t.message}", t)
                        Toast.makeText(context, "네트워크 오류: ${t.message}", Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
enum class SignUpScreenState {
    ID, PASSWORD, NICKNAME
}
