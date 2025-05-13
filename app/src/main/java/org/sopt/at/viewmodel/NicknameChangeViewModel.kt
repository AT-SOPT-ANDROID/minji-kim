package org.sopt.at.viewmodel

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.sopt.at.model.BaseResponse
import org.sopt.at.model.NicknameChangeRequest
import org.sopt.at.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NicknameChangeViewModel : ViewModel() {
    var newNickname by mutableStateOf("")
    var isLoading by mutableStateOf(false)
    var errorMessage by mutableStateOf<String?>(null)

    fun changeNickname(context: Context, onSuccess: () -> Unit) {
        isLoading = true
        errorMessage = null

        val userId = context.getSharedPreferences("auth", Context.MODE_PRIVATE)
            .getInt("userId", -1).toLong()

        val request = NicknameChangeRequest(newNickname)
        RetrofitInstance.userService.updateNickname(userId, request)
            .enqueue(object : Callback<BaseResponse> {
                override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
                    if (response.isSuccessful) {
                        onSuccess()
                    } else {
                        errorMessage = "닉네임 변경 실패"
                    }
                }

                override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
                    isLoading = false
                    errorMessage = "네트워크 오류: ${t.message}"
                }

            })
    }
}
